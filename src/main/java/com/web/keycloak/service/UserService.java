package com.web.keycloak.service;

import com.web.keycloak.model.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public interface UserService {
    public UserEntity getUser(@AuthenticationPrincipal OidcUser oidcUser);
}
