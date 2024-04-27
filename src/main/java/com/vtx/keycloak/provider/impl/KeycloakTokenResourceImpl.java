package com.vtx.keycloak.provider.impl;

import com.vtx.keycloak.exception.KeycloakException;
import com.vtx.keycloak.model.KeycloakProperties;
import com.vtx.keycloak.model.TokenExchange;
import com.vtx.keycloak.provider.KeycloakTokenResource;
import com.vtx.keycloak.utils.KeycloakHelper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Form;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Config;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.BasicAuthFilter;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;

import java.util.Objects;

import static org.keycloak.OAuth2Constants.*;

/**
 * Implementation class for managing Keycloak tokens.
 * Extends {@link AbstractKeycloakSupport} to inherit common methods for accessing Keycloak resources.
 * Implements {@link KeycloakTokenResource} interface for defining token-related operations.
 */
public class KeycloakTokenResourceImpl extends AbstractKeycloakSupport
        implements KeycloakTokenResource {
    private final Config config;
    private final Client client;

    /**
     * Constructs a KeycloakTokenResourceImpl with the specified Keycloak properties, Keycloak instance, configuration, and client.
     *
     * @param properties The {@link KeycloakProperties} containing realm information.
     * @param keycloak   The {@link Keycloak} instance.
     * @param config     The Keycloak configuration.
     * @param client     The HTTP client.
     */
    public KeycloakTokenResourceImpl(
            KeycloakProperties properties, Keycloak keycloak, Config config, Client client
    ) {
        super(properties.getRealm(), keycloak);
        this.config = config;
        this.client = client;
    }

    /**
     * Retrieves the access token.
     *
     * @return The {@link AccessTokenResponse} containing the access token.
     */
    @Override
    public AccessTokenResponse getAccessToken() {
        return keycloak.tokenManager().getAccessToken();
    }

    /**
     * Retrieves an access token using the provided username and password.
     *
     * @param username The username.
     * @param password The password.
     * @return The {@link AccessTokenResponse} containing the access token.
     * @throws KeycloakException If an error occurs during token retrieval.
     */
    @Override
    public AccessTokenResponse getAccessToken(String username, String password)
            throws KeycloakException {
        config.setGrantType(OAuth2Constants.PASSWORD);
        config.setUsername(username);
        config.setPassword(password);
        var tokenManager = new TokenManager(config, client);
        return tokenManager.getAccessToken();
    }

    /**
     * Logs out the user associated with the provided refresh token.
     *
     * @param refreshToken The refresh token.
     * @throws KeycloakException If an error occurs during logout.
     */
    @Override
    public void logout(String refreshToken) throws KeycloakException {
        if (refreshToken != null) {
            var tokenService = KeycloakHelper.getTokenServiceInstance(
                    config,
                    client,
                    new BasicAuthFilter(config.getClientId(), config.getClientSecret())
            );
            var form = new Form();
            form.param(CLIENT_ID, config.getClientId());
            form.param(CLIENT_SECRET, config.getClientSecret());
            form.param(REFRESH_TOKEN, refreshToken);
            tokenService.logout(config.getRealm(), form.asMap());
        }
    }

    /**
     * Exchanges the provided subject token for an access token.
     *
     * @param tokenExchange The token exchange parameters.
     * @return The {@link AccessTokenResponse} containing the access token.
     */
    @Override
    public AccessTokenResponse exchange(TokenExchange tokenExchange) {
        Objects.requireNonNull(tokenExchange);

        var tokenService = KeycloakHelper.getTokenServiceInstance(config, client);
        var form = new Form();

        form.param(CLIENT_ID, config.getClientId());
        form.param(CLIENT_CREDENTIALS, config.getClientSecret());
        form.param(GRANT_TYPE, TOKEN_EXCHANGE_GRANT_TYPE);
        form.param(SUBJECT_TOKEN, tokenExchange.getSubjectToken());
        form.param(SUBJECT_ISSUER, tokenExchange.getSubjectIssuer());
        form.param(SUBJECT_TOKEN_TYPE, tokenExchange.getSubjectTokenType());
        form.param(AUDIENCE, tokenExchange.getAudience());
        form.param(ISSUER, tokenExchange.getRequestedIssuer());
        form.param(REQUESTED_SUBJECT, tokenExchange.getRequestedSubject());
        form.param(SCOPE, tokenExchange.getScope());

        return tokenService.grantToken(config.getRealm(), form.asMap());
    }

    /**
     * Retrieves a new access token using the provided refresh token.
     *
     * @param refreshToken The refresh token.
     * @return The {@link AccessTokenResponse} containing the new access token.
     */
    @Override
    public AccessTokenResponse getRefreshToken(String refreshToken) {
        var tokenService = KeycloakHelper.getTokenServiceInstance(config, client);

        var form = new Form();
        form.param(GRANT_TYPE, REFRESH_TOKEN);
        form.param(REFRESH_TOKEN, refreshToken);

        return tokenService.refreshToken(config.getRealm(), form.asMap());
    }
}
