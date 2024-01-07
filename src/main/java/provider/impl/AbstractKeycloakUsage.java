package provider.impl;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

public class AbstractKeycloakUsage {
    private final String realm;
    protected final Keycloak keycloak;

    public AbstractKeycloakUsage(String realm, Keycloak keycloak) {
        this.realm = realm;
        this.keycloak = keycloak;
    }

    public UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }

    public UserResource getUserResource(String id) {
        return keycloak.realm(realm).users().get(id);
    }

    public RealmsResource getRealmsResource() {
        return keycloak.realms();
    }

    public RealmResource getRealmResource() {
        return keycloak.realm(realm);
    }

    public RolesResource getRolesResource() {
        return keycloak.realm(realm).roles();
    }

    public RoleResource getRoleResource(String role) {
        return keycloak.realm(realm).roles().get(role);
    }
}
