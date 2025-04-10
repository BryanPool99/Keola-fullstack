package com.keola.order_service.repository;

import com.keola.order_service.model.dto.OrderDto;
import com.keola.order_service.model.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveMongoRepository<Order, Integer> {

    @Query(value = "{" +
            "$or: [" +
            "   { $expr: { $eq: [?0, null] } }," +
            "   { $expr: { $eq: [?0, ''] } }," +
            "   { description: { $regex: ?0, $options: 'i' } }" +
            "]" +
            "}", count = true)
    Mono<Long> countOrderListAll(String description);

    @Query(value = "{" +
            "$or: [" +
            "   { $expr: { $eq: [?0, null] } }," +
            "   { $expr: { $eq: [?0, ''] } }," +
            "   { description: { $regex: ?0, $options: 'i' } }" +
            "]" +
            "}", sort = "{ 'orderId': 1 }")
    Flux<OrderDto> findListAllOrderIdAsc(String description, Pageable pageable);

    @Query(value = "{" +
            "$or: [" +
            "   { $expr: { $eq: [?0, null] } }," +
            "   { $expr: { $eq: [?0, ''] } }," +
            "   { description: { $regex: ?0, $options: 'i' } }" +
            "]" +
            "}", sort = "{ 'orderId': -1 }")
    Flux<OrderDto> findListAllOrderIdDesc(String description, Pageable pageable);
}
