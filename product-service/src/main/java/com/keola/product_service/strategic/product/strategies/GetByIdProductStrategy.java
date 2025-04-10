package com.keola.product_service.strategic.product.strategies;

import com.keola.product_service.mapper.ProductMapper;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.repository.ProductRepository;
import com.keola.product_service.strategic.product.ProductStrategy;
import com.keola.product_service.util.enums.product.ProductFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetByIdProductStrategy implements ProductStrategy {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<RetrieveProductResponse> retrieveProduct(
            Map<String, String> values, Integer limit, Integer offset, String sort) {
        Integer productId = Integer.parseInt(values.get(ProductFilterEnum.PRODUCT_ID.getCode()));
        log.info("Start the strategy GetByIdProductStrategy with id: {}", productId);
        return productRepository.findProductById(productId)
                .map(productMapper::toProductResponse)
                .map(productResponse ->
                        RetrieveProductResponse.builder().products(List.of(productResponse)).build())
                .doOnError(error -> log.error("Error in GetByIdProductStrategy: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of the GetByIdProductStrategy"));
    }
}
