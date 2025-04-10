package com.keola.product_service.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductQueryConstants {

    public static final String PRODUCT_QUERY_LIST_ALL = """
            SELECT
                P.product_id AS productid,
                P.description AS description,
                P.aud_est_cod_in AS state,
                P.stock AS stock,
                P.price AS price
            FROM product P
            WHERE (
              :description IS null) or (
              TRIM(UPPER(P.description)) LIKE '%' || TRIM(UPPER(:description)) || '%'
            )
            """;

    public static final String PRODUCT_QUERY_LIST_ALL_COUNT = """
            SELECT
                COUNT(P.product_id)
            FROM product P
            WHERE (
              :description IS null) or (
              TRIM(UPPER(P.description)) LIKE '%' || TRIM(UPPER(:description)) || '%'
            );
            """;

    public static final String LIMIT_OFFSET = """
            LIMIT :limit OFFSET :offset
            """;

    public static final String PRODUCT_QUERY_GET_BY_ID = """
            SELECT
                P.product_id AS productid,
                P.description AS description,
                P.aud_est_cod_in AS state,
                P.stock AS stock,
                P.price AS price
            FROM product P
            WHERE P.product_id = :productId;
            """;

    public static final String PRODUCT_QUERY_LIST_SEARCH = """
            SELECT
                P.product_id AS productid,
                P.description AS description
            FROM product P
            WHERE
            P.aud_est_cod_in = 1
            AND (
              :description IS null) or (
              TRIM(UPPER(P.description)) LIKE '%' || TRIM(UPPER(:description)) || '%'
            )
            """;
}
