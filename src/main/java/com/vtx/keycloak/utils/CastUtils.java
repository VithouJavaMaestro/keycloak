package com.vtx.keycloak.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SuppressWarnings("unchecked")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CastUtils {
    public static <T> T cast(Object value) {
        return (T) value;
    }
}
