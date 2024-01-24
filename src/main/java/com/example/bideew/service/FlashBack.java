package com.example.bideew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bideew.repository.InforRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlashBack {
    
    @Autowired
    private InforRepository inforRepository;

    public ResponseEntity<?> getFlashBacks(){
        return ResponseEntity.ok().body(inforRepository.findAll());
    }

    public ResponseEntity<?> addFlaskBack(){
        return null;
    }

}
