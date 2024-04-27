package com.vtx.keycloak.provider.impl;

import com.vtx.keycloak.model.KeycloakProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import com.vtx.keycloak.provider.KeycloakRoleResource;

import java.util.List;

/**
 * Implementation class for managing Keycloak roles.
 * Extends {@link AbstractKeycloakSupport} to inherit common methods for accessing Keycloak resources.
 * Implements {@link KeycloakRoleResource} interface for defining role-related operations.
 */
public class KeycloakRoleResourceImpl extends AbstractKeycloakSupport
        implements KeycloakRoleResource {

    /**
     * Constructs a KeycloakRoleResourceImpl with the specified Keycloak properties and Keycloak instance.
     *
     * @param properties The {@link KeycloakProperties} containing realm information.
     * @param keycloak   The {@link Keycloak} instance.
     */
    public KeycloakRoleResourceImpl(KeycloakProperties properties, Keycloak keycloak) {
        super(properties.getRealm(), keycloak);
    }

    /**
     * Retrieves a list of all roles in the Keycloak realm.
     *
     * @return A list of {@link RoleRepresentation} objects representing roles.
     */
    @Override
    public List<RoleRepresentation> getRoles() {
        return getRolesResource().list();
    }

    /**
     * Finds a role by name in the Keycloak realm.
     *
     * @param role The name of the role to find.
     * @return The {@link RoleRepresentation} object representing the role if found, null otherwise.
     */
    @Override
    public RoleRepresentation findRoleByName(String role) {
        return getRoleResource(role).toRepresentation();
    }
}
