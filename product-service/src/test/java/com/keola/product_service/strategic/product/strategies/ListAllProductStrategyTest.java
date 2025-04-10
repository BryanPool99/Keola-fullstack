package com.keola.product_service.strategic.product.strategies;

import com.keola.product_service.mapper.ProductMapper;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.model.dto.ProductDto;
import com.keola.product_service.repository.ProductRepository;
import com.keola.product_service.util.enums.product.ProductFilterEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListAllProductStrategyTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    @InjectMocks
    ListAllProductStrategy listAllProductStrategy;

    @Test
    @DisplayName("Return a list of products when the order is id asc")
    void returnAListOfProductsWhenTheOrderIsIdAsc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductIdAsc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "descrption"), 2, 0,
                        "id,asc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is id desc")
    void returnAListOfProductsWhenTheOrderIsIdDesc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductIdDesc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "descrption"), 2, 0,
                        "id,desc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is description asc")
    void returnAListOfProductsWhenTheOrderIsDescriptionAsc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductDescriptionAsc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "descrption"), 2, 0,
                        "description,asc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is description desc")
    void returnAListOfProductsWhenTheOrderIsDescriptionDesc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductDescriptionDesc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "descrption"), 2, 0,
                        "description,desc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is status asc")
    void returnAListOfProductsWhenTheOrderIsStatusAsc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductStatusAsc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "description"), 2, 0,
                        "price,asc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is status desc")
    void returnAListOfProductsWhenTheOrderIsStatusDesc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductStatusDesc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "description"), 2, 0,
                        "status,desc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is price asc")
    void returnAListOfProductsWhenTheOrderIsPriceAsc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductPriceAsc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "description"), 2, 0,
                        "price,asc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is price desc")
    void returnAListOfProductsWhenTheOrderIsPriceDesc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductPriceDesc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "description"), 2, 0,
                        "price,desc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is stock asc")
    void returnAListOfProductsWhenTheOrderIsStockAsc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductStockAsc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "description"), 2, 0,
                        "stock,asc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }

    @Test
    @DisplayName("Return a list of products when the order is stock desc")
    void returnAListOfProductsWhenTheOrderIsStockDesc() {
        var productDto = ProductDto.builder().build();
        var retrieveProductResponse = RetrieveProductResponse.builder().build();

        when(productRepository.findListAllProductStockDesc(anyString(), anyInt(), anyInt()))
                .thenReturn(Flux.just(productDto));

        when(productRepository.countProductListAll(anyString()))
                .thenReturn(Mono.just(1L));

        when(productMapper.toRetrieveProductResponse(any(Stream.class), anyInt(), anyInt(), anyLong()))
                .thenReturn(retrieveProductResponse);

        StepVerifier.create(listAllProductStrategy.retrieveProduct(
                        Map.of(ProductFilterEnum.PRODUCT_FILTER.getCode(), "description"), 2, 0,
                        "stock,desc"))
                .expectNext(retrieveProductResponse)
                .verifyComplete();
    }
}