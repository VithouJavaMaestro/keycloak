package com.vtx.keycloak.config.model.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import validation.PropertiesValidator;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        var validator = context.getBean(grantType.getValue(), PropertiesValidator.class);

        validator.validate(this);
    }
}
