package com.keola.order_service.strategic.order.strategies;

import com.keola.order_service.mapper.OrderMapper;
import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.model.dto.OrderDto;
import com.keola.order_service.repository.OrderRepository;
import com.keola.order_service.strategic.order.OrderStrategy;
import com.keola.order_service.util.enums.order.OrderFilterEnum;
import com.keola.order_service.util.enums.order.OrderSortOrderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListAllOrderStrategy implements OrderStrategy {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Mono<RetrieveOrderResponse> retrieveOrder(
            Map<String, String> values, Integer limit, Integer offset,
            String sort) {
        String description = values.get(OrderFilterEnum.ORDER_FILTER.getCode());
        log.info("Start the strategy ListAllOrderStrategy with description: {}", description);

        return Mono.zip(
                        listAllOrdersFromDB(description, limit, offset, sort).collectList(),
                        orderRepository.countOrderListAll(description)
                )
                .map(tuple2 -> orderMapper.toRetrieveOrderResponse(
                        tuple2.getT1().stream(),
                        limit,
                        offset,
                        tuple2.getT2()
                ))
                .doOnError(error -> log.error("Error in ListAllOrderStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of the ListAllOrderStrategy"));
    }

    private Flux<OrderDto> listAllOrdersFromDB(String filter, Integer limit, Integer offset, String sort) {
        Pageable pageable = PageRequest.of(offset / limit, limit);

        if (sort.isBlank()) {
            log.info("Sort is blank");
            return orderRepository.findListAllOrderIdAsc(filter, pageable);
        }

        Map<String, Function<String, Flux<OrderDto>>> queries = Map.ofEntries(
                Map.entry(OrderSortOrderEnum.ID_ASC.getCode(), field ->
                        orderRepository.findListAllOrderIdAsc(filter, pageable)),
                Map.entry(OrderSortOrderEnum.ID_DESC.getCode(), field ->
                        orderRepository.findListAllOrderIdDesc(filter, pageable))
        );

        return queries.getOrDefault(sort, field -> Flux.defer(() ->
                        Flux.error(new RuntimeException("Bad parameters"))))
                .apply(sort);
    }
}
