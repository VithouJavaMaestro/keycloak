package com.vtx.keycloak.config;

import com.vtx.keycloak.model.KeycloakProperties;
import com.vtx.keycloak.provider.impl.KeycloakGroupResource;
import com.vtx.keycloak.provider.impl.KeycloakRoleResourceImpl;
import com.vtx.keycloak.provider.impl.KeycloakTokenResourceImpl;
import com.vtx.keycloak.provider.impl.KeycloakUserResourceImpl;
import com.vtx.keycloak.validation.ClientCredentialPropertiesValidator;
import com.vtx.keycloak.validation.PasswordGrantTypePropertiesValidator;
import jakarta.ws.rs.client.Client;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Config;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class for integrating with Keycloak.
 * This class provides beans for configuring Keycloak authentication and authorization settings.
 * @author Chanthavithou THEN
 */
@Configuration
@Import({
        KeycloakUserResourceImpl.class,
        ClientCredentialPropertiesValidator.class,
        PasswordGrantTypePropertiesValidator.class,
        KeycloakRoleResourceImpl.class,
        KeycloakTokenResourceImpl.class,
        KeycloakGroupResource.class
})
@EnableConfigurationProperties(KeycloakProperties.class)
public class KeycloakConfiguration {

    /**
     * Configures a Keycloak instance using the provided properties and REST client.
     *
     * @param properties The KeycloakProperties containing authentication and authorization settings.
     * @param client     The REST client for making requests to the Keycloak server.
     * @return Configured Keycloak instance.
     */
    @Bean
    @ConditionalOnMissingBean(Keycloak.class)
    public Keycloak configureKeycloak(
            @Autowired KeycloakProperties properties, @Autowired Client client) {
        return KeycloakBuilder.builder()
                .realm(properties.getRealm())
                .grantType(properties.getGrantType().getValue())
                .serverUrl(properties.getAuthServerUrl())
                .clientId(properties.getClientId())
                .clientSecret(properties.getClientSecret())
                .resteasyClient(client)
                .build();
    }

    /**
     * Configures a Keycloak admin client configuration.
     *
     * @param properties The KeycloakProperties containing authentication and authorization settings.
     * @return Configured Keycloak admin client configuration.
     */
    @Bean
    @ConditionalOnMissingBean(Config.class)
    public Config config(@Autowired KeycloakProperties properties) {
        return new Config(
                properties.getAuthServerUrl(),
                properties.getRealm(),
                null,
                null,
                properties.getClientId(),
                properties.getClientSecret());
    }

    /**
     * Configures a REST client for making HTTP requests.
     *
     * @return Configured REST client.
     */
    @Bean
    public Client configClient() {
        return new ResteasyClientBuilderImpl().connectionPoolSize(10).build();
    }
}
