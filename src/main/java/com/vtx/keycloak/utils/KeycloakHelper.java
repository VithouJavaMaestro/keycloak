package com.vtx.keycloak.utils;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientRequestFilter;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.admin.client.Config;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.BasicAuthFilter;
import org.keycloak.admin.client.token.TokenService;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

/**
 * Helper class providing constants and methods related to Keycloak.
 */
@Getter
@Setter
public class KeycloakHelper {
    public static final String SUB = "sub";
    public static final String RESOURCE_ACCESS = "resource_access";
    public static final String EMAIL_VERIFIED = "email_verified";
    public static final String ALLOWED_ORIGINS = "allowed_origins";
    public static final String ISSUED_URL = "iss";
    public static final String TOKEN_PREFIX = "typ";
    public static final String PREFERRED_USERNAME = "preferred_username";
    public static final String GIVEN_NAME = "given_name";
    public static final String SID = "sid";
    public static final String AUD = "aud";
    public static final String ACR = "acr";
    public static final String REALM_ACCESS = "realm_access";
    public static final String CLIENT_ID = "azp";
    public static final String SCOPE = "scope";
    public static final String NAME = "name";
    public static final String EXP = "exp";
    public static final String SESSION_STATE = "session_state";
    public static final String IAT = "iat";
    public static final String FAMILY_NAME = "family_name";
    public static final String JTI = "jti";
    public static final String EMAIL = "email";
    public static final String KID = "kid";
    public static final String TYPE = "typ";
    public static final String ALGORITHM = "alg";

    private Map<String, Object> claims;
    private Map<String, Object> headers;
    private String token;
    private Instant issuedAt;
    private Instant expiresAt;
    private Set<String> roles;

    /**
     * Retrieves a TokenService instance based on the provided configuration and client.
     * Registers the provided filter if it's not a public client.
     *
     * @param config {@link Config} The Keycloak configuration.
     * @param client  refers to {@link Client}
     * @param filter The client request filter {@link  ClientRequestFilter} (can be null).
     * @return The TokenService instance.
     */
    public static TokenService getTokenServiceInstance(
            Config config, Client client, ClientRequestFilter filter) {
        var target = client.target(config.getServerUrl());
        if (!config.isPublicClient() && filter != null) {
            target.register(filter);
        } else {
            target.register(new BasicAuthFilter(config.getClientId(), config.getClientSecret()));
        }
        return Keycloak.getClientProvider().targetProxy(target, TokenService.class);
    }

    /**
     * Retrieves a TokenService instance based on the provided configuration and client.
     *
     * @param config {@link Config} The Keycloak configuration.
     * @param client  refers to {@link Client}
     * @return The TokenService instance {@link TokenService}.
     */
    public static TokenService getTokenServiceInstance(Config config, Client client) {
        return getTokenServiceInstance(config, client, null);
    }
}

