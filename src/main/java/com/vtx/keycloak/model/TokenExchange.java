package com.vtx.keycloak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class representing token exchange parameters.
 * Provides getters and setters for token exchange properties.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenExchange {
    /**
     * The issuer of the subject token.
     */
    private String subjectIssuer;

    /**
     * The type of the subject token.
     */
    private String subjectTokenType;

    /**
     * The type of token requested in the exchange.
     */
    private String requestedTokenType;

    /**
     * The intended audience of the requested token.
     */
    private String audience;

    /**
     * The issuer of the requested token.
     */
    private String requestedIssuer;

    /**
     * The subject of the requested token.
     */
    private String requestedSubject;

    /**
     * The scope of the requested token.
     */
    private String scope;

    /**
     * The subject token to be exchanged.
     */
    private String subjectToken;
}
