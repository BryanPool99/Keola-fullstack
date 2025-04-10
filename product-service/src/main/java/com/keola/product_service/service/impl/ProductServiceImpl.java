package com.keola.product_service.service.impl;

import com.keola.product_service.model.CreateProductRequest;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.model.UpdateProductRequest;
import com.keola.product_service.model.entity.ProductEntity;
import com.keola.product_service.repository.ProductRepository;
import com.keola.product_service.service.ProductService;
import com.keola.product_service.strategic.product.ProductStrategyFactory;
import com.keola.product_service.util.Util;
import com.keola.product_service.util.enums.ErrorEnum;
import com.keola.product_service.util.enums.product.ProductFilterEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.keola.product_service.util.ExceptionUtil.buildGenesisExceptionMono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductStrategyFactory productStrategyFactory;

    private final ProductRepository productRepository;

    private final TransactionalOperator transactionalOperator;

    @Override
    public Mono<RetrieveProductResponse> listProducts(
            String filter, Integer limit, Integer offset, String sort, String user) {
        log.info("Start the method listProducts: sort: {}, filter:{} , limit: {}, offset: {}", sort, filter, limit,
                offset);
        Map<String, String> values = Util.parseStringToMap(filter);
        String retrieveType = values.get(ProductFilterEnum.PRODUCT_RETRIEVE_TYPE.getCode());
        return productStrategyFactory.getStrategy(retrieveType)
                .retrieveProduct(values, limit, offset, sort)
                .doOnError(error -> log.error("Error in listProducts: {}", error.getMessage()))
                .doOnSuccess(value -> log.info("End of listProducts"));
    }

    @Override
    public Mono<Void> createProduct(CreateProductRequest createProductRequest, String user) {
        log.info("Start the createProduct by user :{}", user);
        return createProductEntity(createProductRequest, user)
                .then()
                .as(transactionalOperator::transactional)
                .doOnError(error -> log.error("Error during createProduct process", error))
                .doOnSuccess(success -> log.info("Product created successfully"));

    }

    private Mono<ProductEntity> createProductEntity(CreateProductRequest createProductRequest, String user) {
        var product = ProductEntity.builder()
                .price(createProductRequest.getPrice())
                .description(createProductRequest.getDescription())
                .stock(createProductRequest.getStock())
                .status(createProductRequest.getStatus())
                .createdBy(user)
                .creationDate(LocalDateTime.now())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> updateProduct(UpdateProductRequest updateProductRequest, String user) {
        log.info("Start the updateProduct by user : {}", user);
        return productRepository.findByProductId(updateProductRequest.getId())
                .switchIfEmpty(Mono.defer(() -> buildGenesisExceptionMono(ErrorEnum.PRODUCT_ID_NOT_EXISTS)))
                .flatMap(productEntity -> updateProductEntity(productEntity, updateProductRequest, user))
                .then()
                .as(transactionalOperator::transactional)
                .doOnError(error -> log.error("Error during updateProduct process", error))
                .doOnSuccess(success -> log.info("Product updated successfully"));

    }

    private Mono<ProductEntity> updateProductEntity(
            ProductEntity productEntity, UpdateProductRequest updateProductRequest, String user) {
        log.info("Start the updateProductEntity by user : {}", user);
        Optional.ofNullable(updateProductRequest.getPrice())
                .ifPresent(productEntity::setPrice);
        Optional.ofNullable(updateProductRequest.getDescription())
                .ifPresent(productEntity::setDescription);
        Optional.ofNullable(updateProductRequest.getStock())
                .ifPresent(productEntity::setStock);
        Optional.ofNullable(updateProductRequest.getStatus())
                .ifPresent(productEntity::setStatus);
        productEntity.setUpdatedBy(user);
        productEntity.setUpdateDate(LocalDateTime.now());

        return productRepository.save(productEntity);
    }
}
