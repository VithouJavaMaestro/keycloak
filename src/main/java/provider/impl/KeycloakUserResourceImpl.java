package provider.impl;

import com.vtx.keycloak.config.model.model.KeycloakProperties;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import provider.KeycloakUserResource;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public class KeycloakUserResourceImpl extends AbstractKeycloakUsage implements KeycloakUserResource {

    public KeycloakUserResourceImpl(KeycloakProperties properties, Keycloak keycloak) {
        super(properties.getRealm(), keycloak);
    }

    @Override
    public Response createUser(UserRepresentation representation) {
        try (var usersResource = getUsersResource().create(representation)) {
            return usersResource;
        }
    }

    @Override
    public void updateUser(String id, UserRepresentation userRepresentation) {
        getUserResource(id).update(userRepresentation);
    }

    @Override
    public UserRepresentation findUserById(String id) {
        return getUserResource(id).toRepresentation();
    }

    @Override
    public void assignRoles(String userId, List<RoleRepresentation> roleRepresentations) {
        getUserResource(userId).roles().realmLevel().add(roleRepresentations);
    }

    @Override
    public Optional<UserRepresentation> findByEmail(String email, boolean exact) {
        return getUsersResource().searchByEmail(email, true)
                .stream().findFirst();
    }

    @Override
    public List<Role> getCurrentUserRoles() {
        return null;
    }
}
