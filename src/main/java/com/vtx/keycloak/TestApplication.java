package com.vtx.keycloak;


import com.vtx.keycloak.config.KeycloakConfiguration;
import com.vtx.keycloak.model.TokenExchange;
import com.vtx.keycloak.provider.KeycloakTokenResource;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Import({
        KeycloakConfiguration.class
})
@RestController
@EnableAsync
@RequiredArgsConstructor
public class TestApplication {
    private final KeycloakTokenResource keycloakTokenResource;

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

    @PostMapping
    public ResponseEntity<AccessTokenResponse> getToken(
            @RequestBody TokenExchange tokenExchange
    ) {
        return ResponseEntity.ok(keycloakTokenResource.exchange(tokenExchange));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponse> getToken(
            @RequestParam("refreshToken") String refreshToken
    ) {
        return ResponseEntity.ok(keycloakTokenResource.getRefreshToken(refreshToken));
    }
}

