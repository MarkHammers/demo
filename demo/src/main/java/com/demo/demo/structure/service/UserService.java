package com.demo.demo.structure.service;

import com.demo.demo.structure.model.domain.User;
import com.demo.demo.structure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }
}