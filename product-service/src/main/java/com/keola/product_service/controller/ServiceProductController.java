package com.keola.product_service.controller;

import com.keola.product_service.api.ProductApi;
import com.keola.product_service.model.CreateProductRequest;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.model.UpdateProductRequest;
import com.keola.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/serviceproduct/v1")
@RequiredArgsConstructor
public class ServiceProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public Mono<ResponseEntity<Void>> createProduct(
            String unICAUser, Mono<CreateProductRequest> createProductRequest,
            ServerWebExchange exchange) {
        return createProductRequest.flatMap(request ->
                        productService.createProduct(request, unICAUser))
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @Override
    public Mono<ResponseEntity<RetrieveProductResponse>> retrieveProduct(
            String unICAUser, String filter, String sort, Integer limit, Integer offset,
            ServerWebExchange exchange) {
        return productService.listProducts(filter, limit, offset, sort, unICAUser)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> updateProduct(
            String unICAUser, Mono<UpdateProductRequest> updateProductRequest, ServerWebExchange exchange) {
        return updateProductRequest.flatMap(request ->
                        productService.updateProduct(request, unICAUser))
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
