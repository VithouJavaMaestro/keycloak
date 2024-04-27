package com.vtx.keycloak.provider;

import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

/**
 * Interface for managing Keycloak roles.
 * Defines methods for retrieving roles and finding roles by name.
 */
public interface KeycloakRoleResource {

    /**
     * Retrieves a list of all roles in the Keycloak realm.
     *
     * @return A list of {@link RoleRepresentation} objects representing roles.
     */
    List<RoleRepresentation> getRoles();

    /**
     * Finds a role by name in the Keycloak realm.
     *
     * @param role The name of the role to find.
     * @return The {@link RoleRepresentation} object representing the role if found, null otherwise.
     */
    RoleRepresentation findRoleByName(String role);
}
