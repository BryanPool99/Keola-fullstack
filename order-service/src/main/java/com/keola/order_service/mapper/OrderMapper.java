package com.keola.order_service.mapper;

import com.keola.order_service.model.MetadataResponse;
import com.keola.order_service.model.OrderReponse;
import com.keola.order_service.model.RetrieveOrderResponse;
import com.keola.order_service.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "orders", source = "orders")
    @Mapping(target = "metadata", expression = "java(toMetadataResponse(limit, offset, totalElements))")
    RetrieveOrderResponse toRetrieveOrderResponse(
            Stream<OrderDto> orders, Integer limit, Integer offset, long totalElements);

    MetadataResponse toMetadataResponse(Integer limit, Integer offset, long totalElements);

    OrderReponse toOrderReponse(OrderDto orderDto);
}
