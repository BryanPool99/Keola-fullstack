package com.keola.product_service.mapper;

import com.keola.product_service.model.MetadataResponse;
import com.keola.product_service.model.ProductResponse;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "products", source = "products")
    @Mapping(target = "metadata", expression = "java(toMetadataResponse(limit, offset, totalElements))")
    RetrieveProductResponse toRetrieveProductResponse(
            Stream<ProductDto> products, Integer limit, Integer offset, long totalElements);

    MetadataResponse toMetadataResponse(Integer limit, Integer offset, long totalElements);

    @Mapping(target = "id", source = "productid")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "status", source = "state")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "price", source = "price")
    ProductResponse toProductResponse(ProductDto productDto);

    List<ProductResponse> toListProductResponse(Stream<ProductDto> productSearchDtos);
}
