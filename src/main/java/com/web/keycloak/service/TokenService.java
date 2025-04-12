package com.web.keycloak.service;

import com.web.keycloak.model.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

public interface TokenService {
    public String generateToken(UserEntity userEntity);
}
