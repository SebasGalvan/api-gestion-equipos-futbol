package com.dux.api.equipos.apiequiposfutbol.configs;

import com.dux.api.equipos.apiequiposfutbol.entity.User;
import com.dux.api.equipos.apiequiposfutbol.repository.IUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultUserInitializer {
    private final BCryptPasswordEncoder passwordEncoder;
    private IUserRepository userRepository;


    private static final String DEFAULT_USERNAME = "test";
    private static final String DEFAULT_PASSWORD = "12345";
    private static final String DEFAULT_FULL_NAME = "Dux user";

    @PostConstruct
    public void initialize() {
        User defaultUser = createDefaultUser();
        userRepository.save(defaultUser);
        System.out.println("API inicializada - Documentacion : http://localhost:8080/docs");
    }

    private User createDefaultUser() {
        User user = new User();
        user.setUsername(DEFAULT_USERNAME);
        user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        user.setFullName(DEFAULT_FULL_NAME);
        return user;
    }

}
