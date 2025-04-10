package com.keola.client_service.strategic.client;

import com.keola.client_service.strategic.client.strategies.GetByIdClienttStrategy;
import com.keola.client_service.strategic.client.strategies.ListAllClientStrategy;
import com.keola.client_service.strategic.client.strategies.SearchClientStrategy;
import com.keola.client_service.util.Util;
import com.keola.client_service.util.enums.client.ClientStrategyTypeEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class ClientStrategyFactory {

    private final Map<ClientStrategyTypeEnum, ClientStrategy> strategies =
            new EnumMap<>(ClientStrategyTypeEnum.class);

    public ClientStrategyFactory(
            ListAllClientStrategy listAllClientStrategy,
            GetByIdClienttStrategy getByIdClienttStrategy,
            SearchClientStrategy searchClientStrategy) {
        strategies.put(ClientStrategyTypeEnum.LIST_ALL_CLIENTS, listAllClientStrategy);
        strategies.put(ClientStrategyTypeEnum.GET_BY_ID_CLIENT, getByIdClienttStrategy);
        strategies.put(ClientStrategyTypeEnum.SEARCH_CLIENTS, searchClientStrategy);
    }

    public ClientStrategy getStrategy(String retrieveType) {
        return strategies.get(Util.getClientStrategic().get(retrieveType));
    }

}
