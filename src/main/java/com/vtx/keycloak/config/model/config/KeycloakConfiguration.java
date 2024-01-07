package com.vtx.keycloak.config.model.config;

import com.vtx.keycloak.config.model.model.KeycloakProperties;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import provider.impl.KeycloakRoleResourceImpl;
import provider.impl.KeycloakUserResourceImpl;
import validation.ClientCredentialPropertiesValidator;
import validation.PasswordGrantTypePropertiesValidator;

@Configuration
@Import({
        KeycloakUserResourceImpl.class,
        ClientCredentialPropertiesValidator.class,
        PasswordGrantTypePropertiesValidator.class,
        KeycloakRoleResourceImpl.class,
})

@EnableConfigurationProperties(KeycloakProperties.class)
public class KeycloakConfiguration {

    @Bean
    public Keycloak configureKeycloak(@Autowired KeycloakProperties properties) {
        return KeycloakBuilder
                .builder()
                .realm(properties.getRealm())
                .grantType(properties.getGrantType().getValue())
                .serverUrl(properties.getAuthServerUrl())
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                .build();
    }
}
