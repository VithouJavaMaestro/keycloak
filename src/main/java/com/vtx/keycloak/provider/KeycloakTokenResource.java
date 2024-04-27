package com.vtx.keycloak.provider;

import com.vtx.keycloak.exception.KeycloakException;
import com.vtx.keycloak.model.TokenExchange;
import org.keycloak.representations.AccessTokenResponse;

/**
 * Interface for managing Keycloak tokens.
 * Defines methods for retrieving access tokens, refreshing tokens, exchanging tokens, and logging out users.
 */
public interface KeycloakTokenResource {

    /**
     * Retrieves an access token.
     *
     * @return The {@link AccessTokenResponse} containing the access token.
     */
    AccessTokenResponse getAccessToken();

    /**
     * Retrieves an access token using the provided username and password.
     *
     * @param username The username.
     * @param password The password.
     * @return The {@link AccessTokenResponse} containing the access token.
     * @throws KeycloakException If an error occurs during token retrieval.
     */
    AccessTokenResponse getAccessToken(String username, String password) throws KeycloakException;

    /**
     * Retrieves a new access token using the provided refresh token.
     *
     * @param refreshToken The refresh token.
     * @return The {@link AccessTokenResponse} containing the new access token.
     */
    AccessTokenResponse getRefreshToken(String refreshToken);

    /**
     * Logs out the user associated with the provided refresh token.
     *
     * @param refreshToken The refresh token.
     * @throws KeycloakException If an error occurs during logout.
     */
    void logout(String refreshToken) throws KeycloakException;

    /**
     * Exchanges the provided subject token for an access token.
     *
     * @param tokenExchange The token exchange parameters.
     * @return The {@link AccessTokenResponse} containing the access token.
     */
    AccessTokenResponse exchange(TokenExchange tokenExchange);
}
