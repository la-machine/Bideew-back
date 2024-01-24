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

import com.example.bideew.service.BigeventService;

@RestController
@RequestMapping("/api/bigevent/")
public class BigEventController {
    @Autowired
    private BigeventService eventService;

    @GetMapping("all")
    public ResponseEntity<?> getAllEvent(){
        return eventService.getAllEvents();
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addEvent(@RequestParam MultipartFile image, @RequestParam String title, 
    @RequestParam String desc ) throws IOException{
        System.out.println("Checking file content" + image.getOriginalFilename());
        return eventService.addEvent(image, title, desc);
    }
}
