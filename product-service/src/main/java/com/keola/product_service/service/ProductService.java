package com.keola.product_service.service;

import com.keola.product_service.model.CreateProductRequest;
import com.keola.product_service.model.RetrieveProductResponse;
import com.keola.product_service.model.UpdateProductRequest;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<RetrieveProductResponse> listProducts(String filter, Integer limit, Integer offset, String sort, String user);

    Mono<Void> createProduct(CreateProductRequest createProductRequest, String user);

    Mono<Void> updateProduct(UpdateProductRequest updateProductRequest, String user);
}
