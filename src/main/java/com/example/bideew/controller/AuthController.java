package com.example.bideew.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bideew.Dto.ContactMessage;
import com.example.bideew.Dto.LoginRequest;
import com.example.bideew.Dto.RegistrationRequest;
import com.example.bideew.Dto.SubscribeRequest;
import com.example.bideew.service.AuthenticationService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private AuthenticationService authService;
    

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        return authService.userRegistration(request);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> sunscribe(@RequestBody SubscribeRequest subscribe){
        return authService.subscribe(subscribe);
        
    }

    @GetMapping("/verify")
    @Transactional
    public ResponseEntity<String> verifyUser(@RequestParam String email, @RequestParam String code) {
        boolean verified = authService.verifyUser(email, code);
        if (verified) {
            return new ResponseEntity<>("User verified", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Verification failed", HttpStatus.BAD_REQUEST);
        }
    }
    

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest authRequest){
        return authService.authenticate(authRequest);
    }
    
    @PostMapping("/contact")
    public void contactForm(@RequestBody ContactMessage message){
        authService.sendMessage(message);
    }


}
