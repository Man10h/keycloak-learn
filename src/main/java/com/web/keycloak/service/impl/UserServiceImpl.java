package com.web.keycloak.service.impl;

import com.web.keycloak.model.UserEntity;
import com.web.keycloak.repository.UserRepository;
import com.web.keycloak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    // Muc dich de tao UserEntity khi dang ki bang keycloak
    // con 1 cach khac la them thuoc tinh trong attribute
    @Override
    public UserEntity getUser(OidcUser oidcUser) {
        String id = oidcUser.getAttribute("sub");
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        UserEntity userEntity;
        if(optionalUser.isEmpty()){
            userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setUsername(oidcUser.getAttribute("preferred_username"));
            userEntity.setBookEntities(new ArrayList<>());
            Collection<? extends GrantedAuthority> authorities = oidcUser.getAuthorities();
            List<String> roles = authorities.stream().map(it -> it.getAuthority()).collect(Collectors.toList());
            String roleName = "";
            for(String authority : roles){
                if(authority.contains("OIDC_")){
                    roleName = authority.substring(5);
                    break;
                }
            }
            userEntity.setRoleName(roleName);
            userRepository.save(userEntity);
            return userEntity;
        }
        return optionalUser.get();
    }
}
