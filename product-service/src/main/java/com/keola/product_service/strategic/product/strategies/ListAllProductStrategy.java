package com.keola.product_service.strategic.product.strategies;

import com.keola.product_service.mapper.ProductMapper;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.model.dto.ProductDto;
import com.keola.product_service.repository.ProductRepository;
import com.keola.product_service.strategic.product.ProductStrategy;
import com.keola.product_service.util.enums.product.ProductFilterEnum;
import com.keola.product_service.util.enums.product.ProductSortOrderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListAllProductStrategy implements ProductStrategy {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<RetrieveProductResponse> retrieveProduct(
            Map<String, String> values, Integer limit, Integer offset, String sort) {
        String description = values.get(ProductFilterEnum.PRODUCT_FILTER.getCode());
        log.info("Start the strategy ListAllProductStrategy with description: {}", description);
        return Mono.zip(listAllProductsFromDB(description, limit, offset, sort).collectList(),
                        productRepository.countProductListAll(description))
                .map(tuple2 -> productMapper.toRetrieveProductResponse(tuple2.getT1().stream(),
                        limit, offset, tuple2.getT2()))
                .doOnError(error -> log.error("Error in listAllProductStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of the listAllProductStrategy"));
    }

    private Flux<ProductDto> listAllProductsFromDB(String filter, Integer limit, Integer offset, String sort) {
        if (sort.isBlank()) {
            return productRepository.findListAllProductIdAsc(filter, limit, offset);
        }
        Map<String, Function<String, Flux<ProductDto>>> queries = Map.ofEntries(
                Map.entry(ProductSortOrderEnum.ID_ASC.getCode(), field ->
                        productRepository.findListAllProductIdAsc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.ID_DESC.getCode(), field ->
                        productRepository.findListAllProductIdDesc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.DESCRIPTION_ASC.getCode(), field ->
                        productRepository.findListAllProductDescriptionAsc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.DESCRIPTION_DESC.getCode(), field ->
                        productRepository.findListAllProductDescriptionDesc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.STATUS_ASC.getCode(), field ->
                        productRepository.findListAllProductStatusAsc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.STATUS_DESC.getCode(), field ->
                        productRepository.findListAllProductStatusDesc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.PRICE_ASC.getCode(), field ->
                        productRepository.findListAllProductPriceAsc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.PRICE_DESC.getCode(), field ->
                        productRepository.findListAllProductPriceDesc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.STOCK_ASC.getCode(), field ->
                        productRepository.findListAllProductStockAsc(filter, limit, offset)),
                Map.entry(ProductSortOrderEnum.STOCK_DESC.getCode(), field ->
                        productRepository.findListAllProductStockDesc(filter, limit, offset))
        );
        return queries.getOrDefault(sort, field -> Flux.defer(() ->
                Flux.error(new RuntimeException("Bad parameters")))).apply(sort);
    }
}
