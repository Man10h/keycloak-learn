package com.web.keycloak.service.impl;


import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import com.web.keycloak.model.UserEntity;
import com.web.keycloak.service.TokenService;
import org.keycloak.RSATokenVerifier;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${secretKey}")
    private String secretKey;

    @Override
    public String generateToken(UserEntity userEntity) {
        String username = userEntity.getUsername();
        return "";
    }
}
