package com.dux.api.equipos.apiequiposfutbol.service;

import com.dux.api.equipos.apiequiposfutbol.entity.User;
import com.dux.api.equipos.apiequiposfutbol.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user =  userRepository.findByUsername(username);
        Assert.isTrue(user.isPresent(), "Usuario no encontrado.");
        return user;
    }

}
