package com.keola.product_service.util;

import com.keola.product_service.util.constants.Constants;
import com.keola.product_service.util.enums.product.ProductStrategyTypeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {
    public static Map<String, String> parseStringToMap(String input) {
        return Optional.ofNullable(input)
                .filter(value -> !value.isEmpty())
                .map(value -> Stream.of(value.split(Constants.COMMA))
                        .map(entry -> entry.split(Constants.COLON))
                        .collect(Collectors.toMap(
                                parts -> parts[0].trim(), parts -> parts.length > 1 ? parts[1].trim() :
                                        Constants.EMPTY,
                                (existing, replacement) -> replacement, HashMap::new
                        ))
                )
                .orElseGet(HashMap::new);
    }

    public static Map<String, ProductStrategyTypeEnum> getProductStrategic() {
        return Arrays.stream(ProductStrategyTypeEnum.values())
                .collect(Collectors.toMap(ProductStrategyTypeEnum::getCode,
                        productStrategyTypeEnum -> productStrategyTypeEnum));
    }
}
