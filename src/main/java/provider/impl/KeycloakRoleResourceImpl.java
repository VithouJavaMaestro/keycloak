package provider.impl;

import com.vtx.keycloak.config.model.model.KeycloakProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import provider.KeycloakRoleResource;

import java.util.List;

public class KeycloakRoleResourceImpl extends AbstractKeycloakUsage implements KeycloakRoleResource {
    public KeycloakRoleResourceImpl(KeycloakProperties properties, Keycloak keycloak) {
        super(properties.getRealm(), keycloak);
    }

    @Override
    public List<RoleRepresentation> getRoles() {
        return getRolesResource().list();
    }

    @Override
    public RoleRepresentation findRoleByName(String role) {
        return getRoleResource(role).toRepresentation();
    }
}
