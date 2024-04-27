package com.vtx.keycloak.validation;

import com.vtx.keycloak.model.KeycloakProperties;

public interface PropertiesValidator {
    void validate(KeycloakProperties properties);
}
