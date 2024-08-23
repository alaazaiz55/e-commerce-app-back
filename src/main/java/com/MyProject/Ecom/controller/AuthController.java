package com.MyProject.Ecom.controller;


import com.MyProject.Ecom.dto.AuthReponse;
import com.MyProject.Ecom.dto.AuthRequest;
import com.MyProject.Ecom.dto.RegisterRequest;
import com.MyProject.Ecom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService service ;
    @PostMapping("/register")
    public ResponseEntity<AuthReponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthReponse> authenticate(
            @RequestBody AuthRequest request
    ) {

        return ResponseEntity.ok(service.authenticate(request));

    }




}
