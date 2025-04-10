package com.keola.product_service.repository;

import com.keola.product_service.model.dto.ProductDto;
import com.keola.product_service.model.entity.ProductEntity;
import com.keola.product_service.util.constants.ProductQueryConstants;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends R2dbcRepository<ProductEntity, Integer> {

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL_COUNT)
    Mono<Long> countProductListAll(String description);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY productid ASC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductIdAsc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY productid DESC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductIdDesc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY description ASC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductDescriptionAsc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY description DESC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductDescriptionDesc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY state ASC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductStatusAsc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY state DESC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductStatusDesc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY price ASC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductPriceAsc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY price DESC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductPriceDesc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY stock ASC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductStockAsc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_ALL + " ORDER BY stock DESC " +
            ProductQueryConstants.LIMIT_OFFSET)
    Flux<ProductDto> findListAllProductStockDesc(String description, Integer limit, Integer offset);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_LIST_SEARCH)
    Flux<ProductDto> findListProductsSearch(String description);

    @Query(value = ProductQueryConstants.PRODUCT_QUERY_GET_BY_ID)
    Mono<ProductDto> findProductById(Integer productId);

    Mono<ProductEntity> findByProductId(Integer productId);
}

