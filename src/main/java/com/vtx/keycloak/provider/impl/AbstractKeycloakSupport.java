package com.vtx.keycloak.provider.impl;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

/**
 * Abstract base class for Keycloak support providers.
 * Provides common methods for accessing Keycloak resources such as users, roles, and realms.
 */
public abstract class AbstractKeycloakSupport {
    private final String realm;
    protected final Keycloak keycloak;

    /**
     * Constructs an AbstractKeycloakSupport with the specified realm and Keycloak instance.
     *
     * @param realm    The Keycloak realm .
     * @param keycloak The Keycloak instance {@link Keycloak}.
     */
    public AbstractKeycloakSupport(String realm, Keycloak keycloak) {
        this.realm = realm;
        this.keycloak = keycloak;
    }

    /**
     * Retrieves the resource for managing users in the specified realm.
     *
     * @return The resource for managing users {@link UsersResource}.
     */
    public UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }

    /**
     * Retrieves the resource for managing a specific user in the specified realm.
     *
     * @param id The ID of the user.
     * @return The resource for managing the user {@link UserResource}..
     */
    public UserResource getUserResource(String id) {
        return keycloak.realm(realm).users().get(id);
    }

    /**
     * Retrieves the resource for managing realms.
     *
     * @return The resource for managing realms {@link RealmsResource}.
     */
    public RealmsResource getRealmsResource() {
        return keycloak.realms();
    }

    /**
     * Retrieves the resource for managing the specified realm.
     *
     * @return The resource for managing the realm {@link RealmsResource}..
     */
    public RealmResource getRealmResource() {
        return keycloak.realm(realm);
    }

    /**
     * Retrieves the resource for managing roles in the specified realm.
     *
     * @return The resource for managing roles {@link RolesResource}.
     */
    public RolesResource getRolesResource() {
        return keycloak.realm(realm).roles();
    }

    /**
     * Retrieves the resource for managing a specific role in the specified realm.
     *
     * @param role The name of the role.
     * @return The resource for managing the role {@link RoleResource}.
     */
    public RoleResource getRoleResource(String role) {
        return keycloak.realm(realm).roles().get(role);
    }
}
