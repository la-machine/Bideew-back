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

import com.example.bideew.service.ZeInsideService;

@RestController
@RequestMapping("/api/zeinside/")
public class ZeInsideController {
    @Autowired
    private ZeInsideService zeInsideService;

    @GetMapping("all")
    public ResponseEntity<?> getAllNews() throws IOException{
        return zeInsideService.getAllInterviews();
    }

    @PostMapping("addInterview")
    public ResponseEntity<?> addInterview(@RequestParam MultipartFile image, @RequestParam MultipartFile audio, @RequestParam String title, 
    @RequestParam String desc, @RequestParam MultipartFile video){
        try {
            return zeInsideService.createInterview(video, audio, image, title, desc);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> updateInterview(@RequestParam MultipartFile image, @RequestParam MultipartFile audio, @RequestParam String title, 
    @RequestParam String desc, @RequestParam MultipartFile video){
        try {
            return zeInsideService.updateInterview(video, audio, image, title, desc);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    
}
