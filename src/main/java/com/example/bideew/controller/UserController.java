package com.example.bideew.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bideew.service.InforService;
import com.example.bideew.service.UserService;
import com.example.bideew.service.ZeInsideService;

// @CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private InforService inforService;
    @Autowired
    private UserService userService;
    @Autowired
    private ZeInsideService interview;
    

    @GetMapping("all-user")
    public ResponseEntity<?> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/podcasts")
    public ResponseEntity<?> getAllPodcast() throws IOException{
        return inforService.getAllPodcast();
    }

    @GetMapping("/find/{title:.+}")
    public ResponseEntity<?> getPodcast(@PathVariable String title) throws IOException{
        System.out.println("Testing title value "+title);
        return inforService.getPodcast(title);
    }

    @PostMapping("/addpodcast")
    public ResponseEntity<?> addPodcast(@RequestParam MultipartFile image, @RequestParam MultipartFile audio, @RequestParam String title, 
    @RequestParam String desc ) throws IOException{
        System.out.println("Checking file content" + image.getOriginalFilename());
        return inforService.uploadPodcast(audio,image, title, desc);
    }

    @PostMapping("/update/podcast")
    public ResponseEntity<?> updatePodcast(@RequestParam("image") MultipartFile img,@RequestParam("audio") MultipartFile podcast, @RequestParam String title, 
    @RequestParam String desc ) throws IOException{
        return inforService.updatePodcast(podcast,img, title, desc);
    }


    @PostMapping("/create-interview")
    public ResponseEntity<?> addInterview(@RequestParam("image") MultipartFile interview_img,@RequestParam("audio") MultipartFile interview_audio,@RequestParam("audio") MultipartFile interview_video,
        @RequestParam String title, @RequestParam String desc ) throws IOException{
        return interview.createInterview(interview_video,interview_audio,interview_img, title, desc);
    }

    @GetMapping("/interviews")
    public ResponseEntity<?> getInterviews() throws IOException{
        return interview.getAllInterviews();
    }

    @GetMapping("delete/{title:.+}")
    public ResponseEntity<?> deletePodcast(@PathVariable String title){
        return inforService.deletePodcast(title);
    }
    
}
