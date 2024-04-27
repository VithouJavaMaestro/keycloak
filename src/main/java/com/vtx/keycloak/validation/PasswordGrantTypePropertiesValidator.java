package com.vtx.keycloak.validation;

import com.vtx.keycloak.model.KeycloakProperties;
import org.keycloak.OAuth2Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component(OAuth2Constants.PASSWORD)
public class PasswordGrantTypePropertiesValidator extends ClientCredentialPropertiesValidator {

    public void validate(KeycloakProperties properties) {

        super.validate(properties);

        if (!StringUtils.hasText(properties.getUsername())) {
            throw new IllegalArgumentException("Username required!");
        }

        if (!StringUtils.hasText(properties.getPassword())) {
            throw new IllegalArgumentException("Password required!");
        }
    }
}
