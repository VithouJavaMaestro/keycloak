package validation;

import com.vtx.keycloak.config.model.model.KeycloakProperties;

public interface PropertiesValidator {
    void validate(KeycloakProperties properties);
}
