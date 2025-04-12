package com.web.keycloak.controller;

import com.web.keycloak.model.BookEntity;
import com.web.keycloak.model.UserEntity;
import com.web.keycloak.service.BookService;
import com.web.keycloak.service.TokenService;
import com.web.keycloak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private BookService bookService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/public")
    public String publicPage() {
        return "Đây là trang công khai!";
    }

    @GetMapping("/secured")
    public String securedPage(@AuthenticationPrincipal OAuth2User principal) {
        return "Xin chào, " + principal.getAttribute("preferred_username") + "!";
    }

    // API đăng nhập bằng tài khoản/mật khẩu (chuyển hướng đến Keycloak form mặc định)
    @GetMapping("/login-local")
    public String loginLocal() {
        // Chuyển hướng đến trang đăng nhập Keycloak mặc định
        return "redirect:/oauth2/authorization/keycloak";
    }

    // API đăng nhập qua bên thứ 3 (ví dụ: Google, đã cấu hình trong Keycloak)
    @GetMapping("/login-third-party")
    public String loginThirdParty() {
        // Chuyển hướng đến Identity Provider bên thứ 3 (Google trong ví dụ)
        return "redirect:/oauth2/code/google";
    }

    @GetMapping("/findAll")
    public List<BookEntity> findAll() {
        return bookService.findAll();
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody BookEntity book) {
        return bookService.addBook(book);
    }

    @GetMapping("/user")
    public ResponseEntity<UserEntity> getUser(@AuthenticationPrincipal OidcUser oidcUser) {
        return ResponseEntity.ok(userService.getUser(oidcUser));
    }


}