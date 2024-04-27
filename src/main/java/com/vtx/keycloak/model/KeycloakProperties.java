package com.vtx.keycloak.model;

import com.vtx.keycloak.validation.ClientCredentialPropertiesValidator;
import com.vtx.keycloak.validation.PasswordGrantTypePropertiesValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.vtx.keycloak.validation.PropertiesValidator;

/**
 * Model class representing Keycloak configuration properties.
 * Provides getters and setters for Keycloak configuration properties.
 * Also implements InitializingBean and ApplicationContextAware interfaces for additional configuration validation.
 * @author Chanthavithou THEN
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("vtx.keycloak")
public class KeycloakProperties implements InitializingBean, ApplicationContextAware {
    private ApplicationContext context;
    private String realm;
    private String clientId;
    private String clientSecret;
    private String authServerUrl;
    private GrantType grantType;
    private String username;
    private String password;

    /**
     * Sets the application context.
     *
     * @param applicationContext The application context.
     * @throws BeansException If an error occurs while setting the application context.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * Validates the Keycloak configuration properties.
     * Invoked after the properties are set.
     */
    @Override
    public void afterPropertiesSet()  {
        var validator = new ClientCredentialPropertiesValidator();
        validator.validate(this);
    }
}
