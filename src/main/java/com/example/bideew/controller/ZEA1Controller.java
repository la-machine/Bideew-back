package com.example.bideew.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bideew.service.ZeA1Service;

@RestController
@RequestMapping("/api/zea1/")
public class ZEA1Controller {
    @Autowired
    private ZeA1Service zea1Service;

    @GetMapping("all")
    public ResponseEntity<?> getAllNews() throws IOException{
        return zea1Service.getAllZeA1();
    }

    @PostMapping("add")
    public ResponseEntity<?> addInterview(@RequestParam MultipartFile image, @RequestParam String title, 
    @RequestParam String desc){
        try {
            return zea1Service.createA1(image, title, desc);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> updateInterview(@RequestParam MultipartFile image, @RequestParam MultipartFile audio, @RequestParam String title, 
    @RequestParam String desc, @RequestParam MultipartFile video){
        try {
            return zea1Service.update(image, title, desc);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    
}
