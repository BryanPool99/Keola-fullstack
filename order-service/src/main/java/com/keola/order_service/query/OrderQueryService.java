package com.keola.order_service.query;

import com.keola.order_service.model.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderQueryService {

    private final ReactiveMongoTemplate mongoTemplate;

    public Flux<OrderDto> fetchOrders(Map<String, String> filters, Integer limit, Integer offset, String sort) {
        Query query = new Query();

        // Aplicar el filtro (retrieveType:LAO)
        applyFilter(query, filters);

        // Aplicar el sort
        applySort(query, sort);

        // Aplicar la paginación
        applyPagination(query, limit, offset);

        // Ejecutar la consulta
        return mongoTemplate.find(query, OrderDto.class);
    }

    // Aplicar los filtros (ej. retrieveType:LAO)
    private void applyFilter(Query query, Map<String, String> filters) {
        String retrieveType = filters.get("retrieveType");
        if (retrieveType != null) {
            query.addCriteria(Criteria.where("retrieveType").is(retrieveType));
        }
    }

    // Aplicar la ordenación (ej. ascendente o descendente)
    private void applySort(Query query, String sort) {
        if ("asc".equalsIgnoreCase(sort)) {
            query.with(Sort.by(Sort.Order.asc("orderId")));
        } else if ("desc".equalsIgnoreCase(sort)) {
            query.with(Sort.by(Sort.Order.desc("orderId")));
        }
    }

    // Aplicar la paginación
    private void applyPagination(Query query, Integer limit, Integer offset) {
        query.skip(offset).limit(limit);
    }

    // Obtener el número total de pedidos (para la paginación)
    public Mono<Long> countOrders(Map<String, String> filters) {
        Query query = new Query();
        applyFilter(query, filters);
        return mongoTemplate.count(query, OrderDto.class);
    }
}
