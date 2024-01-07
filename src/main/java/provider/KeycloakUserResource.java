package provider;

import com.vtx.keycloak.config.model.exception.KeycloakException;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface KeycloakUserResource {
    Response createUser(UserRepresentation userRepresentation);

    void updateUser(String id, UserRepresentation userRepresentation) throws KeycloakException;

    UserRepresentation findUserById(String id);

    void assignRoles(String userId, List<RoleRepresentation> roleRepresentations);

    Optional<UserRepresentation> findByEmail(String email, boolean exact);

    List<Role> getCurrentUserRoles();
}
