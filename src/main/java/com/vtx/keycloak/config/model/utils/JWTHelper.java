package com.vtx.keycloak.config.model.utils;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JWTHelper {
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

    private List<String> roles;

    public JWTHelper(Object principal) {
        if (principal instanceof Jwt jwt) {
            this.claims = jwt.getClaims();
            this.expiresAt = jwt.getExpiresAt();
            this.headers = jwt.getHeaders();
            this.issuedAt = jwt.getIssuedAt();
            this.token = jwt.getTokenValue();
            var realmAccess = CastUtils.<LinkedTreeMap<String, List<String>>>cast(claims.get("realm_access"));
            this.roles = realmAccess.get("roles");
        }
    }
}
