package com.example.bideew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bideew.model.Role;
import com.example.bideew.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(userRepository.findAll());
    }

    public ResponseEntity<?> getSubscribers(){
        return ResponseEntity.status(200).body(userRepository.findByRole(Role.Subscriber));
    }
}