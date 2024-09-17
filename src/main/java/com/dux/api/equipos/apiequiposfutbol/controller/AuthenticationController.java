package com.dux.api.equipos.apiequiposfutbol.controller;

import com.dux.api.equipos.apiequiposfutbol.annotations.LoginAnnotation;
import com.dux.api.equipos.apiequiposfutbol.dtos.LoginUserDTO;
import com.dux.api.equipos.apiequiposfutbol.dtos.RegisterUserDto;
import com.dux.api.equipos.apiequiposfutbol.entity.User;
import com.dux.api.equipos.apiequiposfutbol.responses.LoginResponse;
import com.dux.api.equipos.apiequiposfutbol.service.JwtService;
import com.dux.api.equipos.apiequiposfutbol.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Autenticación",
        description = "Endpoints para gestionar el acceso y registro de usuarios en el sistema."
)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService
            authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @LoginAnnotation
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    @Hidden
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }
}
