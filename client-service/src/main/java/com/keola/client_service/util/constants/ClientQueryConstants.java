package com.keola.client_service.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientQueryConstants {

    public static final String CLIENT_QUERY_LIST_ALL = """
            SELECT
                c.client_id AS clientid,
                c.first_name AS firstname,
                c.last_name AS lastname,
                c.username AS username,
                c.email AS email,
                c.phone AS phone,
                c.mobile AS mobile,
                c.dni AS dni,
                c.aud_est_cod_in AS state
            FROM
                client c
            WHERE
                (:description IS NULL)
                OR
                (TRIM(UPPER(c.username)) LIKE '%' || TRIM(UPPER(:description)) || '%')
            """;

    public static final String CLIENT_QUERY_LIST_ALL_COUNT = """
            SELECT
                COUNT(c.client_id)
            FROM
                client c
            WHERE
                (:description IS NULL)
                OR
                (TRIM(UPPER(c.username)) LIKE '%' || TRIM(UPPER(:description)) || '%');
            """;

    public static final String LIMIT_OFFSET = """
            LIMIT :limit OFFSET :offset
            """;

    public static final String CLIENT_QUERY_GET_BY_ID = """
            SELECT
                c.client_id AS clientid,
                c.first_name AS firstname,
                c.last_name AS lastname,
                c.username AS username,
                c.email AS email,
                c.phone AS phone,
                c.mobile AS mobile,
                c.dni AS dni,
                c.aud_est_cod_in AS state
            FROM
                client c
            WHERE c.client_id = :clientId;
            """;

    public static final String CLIENT_QUERY_LIST_SEARCH = """
             SELECT
                c.client_id AS clientid,
                c.username AS username
            FROM
                client c
            WHERE
            c.aud_est_cod_in = 1
            AND (
              :description IS null) or (
              TRIM(UPPER(c.username)) LIKE '%' || TRIM(UPPER(:description)) || '%'
            )
            """;
}
