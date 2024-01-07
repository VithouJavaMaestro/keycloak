package provider;

import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface KeycloakRoleResource {
    List<RoleRepresentation> getRoles();

    RoleRepresentation findRoleByName(String role);
}
