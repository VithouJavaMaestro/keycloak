package com.vtx.keycloak.config.model.model;

import lombok.Getter;
import org.keycloak.OAuth2Constants;

@Getter
public enum GrantType {
    PASSWORD(OAuth2Constants.PASSWORD), CLIENT_CREDENTIALS(OAuth2Constants.CLIENT_CREDENTIALS);

    private final String value;

    GrantType(String value) {
        this.value = value;
    }
}
