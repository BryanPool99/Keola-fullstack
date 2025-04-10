package com.keola.order_service.strategic.order;

import com.keola.order_service.strategic.order.strategies.GetByIdOrdertStrategy;
import com.keola.order_service.strategic.order.strategies.ListAllOrderStrategy;
import com.keola.order_service.util.Util;
import com.keola.order_service.util.enums.order.OrderStrategyTypeEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class OrderStrategyFactory {

    private final Map<OrderStrategyTypeEnum, OrderStrategy> strategies =
            new EnumMap<>(OrderStrategyTypeEnum.class);

    public OrderStrategyFactory(
            ListAllOrderStrategy listAllOrderStrategy,
            GetByIdOrdertStrategy getByIdOrdertStrategy) {
        strategies.put(OrderStrategyTypeEnum.LIST_ALL_ORDERS, listAllOrderStrategy);
        strategies.put(OrderStrategyTypeEnum.GET_BY_ID_ORDER, getByIdOrdertStrategy);
    }

    public OrderStrategy getStrategy(String retrieveType) {
        return strategies.get(Util.getOrderStrategic().get(retrieveType));
    }

}
