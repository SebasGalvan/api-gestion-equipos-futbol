package com.dux.api.equipos.apiequiposfutbol.controller;

import com.dux.api.equipos.apiequiposfutbol.controller.advice.GlobalExceptionHandler;
import com.dux.api.equipos.apiequiposfutbol.entity.User;
import com.dux.api.equipos.apiequiposfutbol.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api")
@AllArgsConstructor
public class UserController extends GlobalExceptionHandler {

    private UserService userService;

    @Hidden
    @GetMapping("/users")
    public List<User> ususarios() {
        return userService.findAll();
    }

    @Hidden
    @GetMapping("/usersByUsername")
    public Optional<User> findByUsername(@RequestParam(name = "username") String username) {
        return userService.findByUsername(username);
    }
}
