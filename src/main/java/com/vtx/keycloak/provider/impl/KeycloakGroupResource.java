package com.vtx.keycloak.provider.impl;

import com.vtx.keycloak.model.KeycloakProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;

public class KeycloakGroupResource extends AbstractKeycloakSupport {
    public KeycloakGroupResource(
            KeycloakProperties properties, Keycloak keycloak
    ) {
        super(properties.getRealm(), keycloak);
    }

    private void createGroup(GroupRepresentation groupRepresentation) {
        try(final var response = getRealmResource().groups().add(groupRepresentation)) {

        }
    }
}
