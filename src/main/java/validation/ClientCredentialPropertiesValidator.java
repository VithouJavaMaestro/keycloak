package validation;

import com.vtx.keycloak.config.model.model.KeycloakProperties;
import org.keycloak.OAuth2Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Component(OAuth2Constants.CLIENT_CREDENTIALS)
public class ClientCredentialPropertiesValidator implements PropertiesValidator {
    @Override
    public void validate(KeycloakProperties properties) {
        var grantTypes = List.of(OAuth2Constants.PASSWORD, OAuth2Constants.CLIENT_CREDENTIALS);

        if (!StringUtils.hasText(properties.getAuthServerUrl())) {
            throw new IllegalArgumentException("Server url required!");
        }

        if (!StringUtils.hasText(properties.getRealm())) {
            throw new IllegalArgumentException("Realm required!");
        }

        if (!StringUtils.hasText(properties.getClientId())) {
            throw new IllegalArgumentException("Client id required!");
        }

        if (!StringUtils.hasText(properties.getClientSecret())) {
            throw new IllegalArgumentException("Client secret required!");
        }

        if (!CollectionUtils.contains(grantTypes.iterator(), properties.getGrantType().getValue())) {
            throw new IllegalArgumentException("Grant type must be valid");
        }
    }
}
