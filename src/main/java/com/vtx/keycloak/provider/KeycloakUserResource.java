package com.vtx.keycloak.provider;

import com.vtx.keycloak.exception.KeycloakException;
import com.vtx.keycloak.model.FilterCriteria;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

/**
 * Interface for managing Keycloak users.
 * Defines methods for creating, updating, finding users, assigning roles, and searching by email.
 */
public interface KeycloakUserResource {

    /**
     * Creates a new user with the provided user representation.
     *
     * @param userRepresentation The representation of the user to be created.
     * @return The response containing information about the created user.
     */
    Response createUser(UserRepresentation userRepresentation);

    /**
     * Updates an existing user with the provided user representation.
     *
     * @param id                 The ID of the user to be updated.
     * @param userRepresentation The representation of the user with updated information.
     * @throws KeycloakException If an error occurs during user update.
     */
    void updateUser(String id, UserRepresentation userRepresentation) throws KeycloakException;

    /**
     * Finds a user by ID.
     *
     * @param id The ID of the user to find.
     * @return The representation of the found user.
     */
    UserRepresentation findUserById(String id);

    /**
     * Assigns roles to a user.
     *
     * @param userId              The ID of the user to whom roles will be assigned.
     * @param roleRepresentations The representations of roles to be assigned.
     */
    void assignRoles(String userId, List<RoleRepresentation> roleRepresentations);

    /**
     * Finds users by email address.
     *
     * @param criteria refers to {@link FilterCriteria}
     * @return A list of user representations matching the search criteria.
     */
    List<UserRepresentation> find(FilterCriteria criteria);
}
