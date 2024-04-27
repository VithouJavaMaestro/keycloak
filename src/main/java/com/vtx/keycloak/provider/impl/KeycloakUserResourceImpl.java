package com.vtx.keycloak.provider.impl;

import com.vtx.keycloak.model.FilterCriteria;
import com.vtx.keycloak.model.KeycloakProperties;
import com.vtx.keycloak.provider.KeycloakUserResource;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

/**
 * Implementation class for managing Keycloak users.
 * Extends {@link AbstractKeycloakSupport} to inherit common methods for accessing Keycloak resources.
 * Implements {@link KeycloakUserResource} interface for defining user-related operations.
 */
@Slf4j
public class KeycloakUserResourceImpl extends AbstractKeycloakSupport implements KeycloakUserResource {

    /**
     * Constructs a KeycloakUserResourceImpl with the specified Keycloak properties and Keycloak instance.
     *
     * @param properties The {@link KeycloakProperties} containing realm information.
     * @param keycloak   The {@link Keycloak} instance.
     */
    public KeycloakUserResourceImpl(KeycloakProperties properties, Keycloak keycloak) {
        super(properties.getRealm(), keycloak);
    }

    /**
     * Creates a new user using the provided user representation.
     *
     * @param representation The representation of the user to be created.
     * @return The response containing information about the created user.
     */
    @Override
    public Response createUser(UserRepresentation representation) {
        try (var usersResource = getUsersResource().create(representation)) {
            return usersResource;
        }
    }

    /**
     * Updates an existing user with the provided user representation.
     *
     * @param id                 The ID of the user to be updated.
     * @param userRepresentation The representation of the user with updated information.
     */
    @Override
    public void updateUser(String id, UserRepresentation userRepresentation) {
        getUserResource(id).update(userRepresentation);
    }

    /**
     * Finds a user by ID.
     *
     * @param id The ID of the user to find.
     * @return The representation of the found user.
     */
    @Override
    public UserRepresentation findUserById(String id) {
        return getUserResource(id).toRepresentation();
    }

    /**
     * Assigns roles to a user.
     *
     * @param userId              The ID of the user to whom roles will be assigned.
     * @param roleRepresentations The representations of roles to be assigned.
     */
    @Override
    public void assignRoles(String userId, List<RoleRepresentation> roleRepresentations) {
        getUserResource(userId).roles().realmLevel().add(roleRepresentations);
    }

    @Override
    public List<UserRepresentation> find(FilterCriteria criteria) {
        return null;
    }
}
