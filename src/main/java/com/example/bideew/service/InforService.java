package com.example.bideew.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bideew.Dto.PodcastResponse;
import com.example.bideew.model.Infor;
import com.example.bideew.model.Type;
import com.example.bideew.repository.InforRepository;

@Service
public class InforService {
    
    @Autowired
    private InforRepository inforRepository;

    public ResponseEntity<?> uploadPodcast(MultipartFile podcast,MultipartFile img, String title, String description) throws IOException{
        Infor tempInfor = inforRepository.findByTitle(title);
        if(tempInfor != null){
            return ResponseEntity.status(400).body("Podcast with this title already exist");
        }
        Infor infor = Infor.builder().title(title).description(description).evenType(Type.Podcast)
        .audio(FileUploadUtil.saveFile(podcast.getOriginalFilename(), podcast))
        .image(FileUploadUtil.saveFile(img.getOriginalFilename(), img)).build();
        inforRepository.save(infor);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getPodcast(String title) throws IOException{
        Infor infor = inforRepository.findByTitle(title);
        if (infor == null || !infor.getEnable()) {
            return ResponseEntity.badRequest().body("No podcast with this title");
        }
        PodcastResponse response = PodcastResponse.builder().title(infor.getTitle())
            .audio(infor.getAudio()).desc(infor.getDescription()).img(infor.getImage()).build();

        return ResponseEntity.status(200).body(response);
        
    }

    public ResponseEntity<?> getAllPodcast() throws IOException{
        List<Infor> infors = inforRepository.findByEvenType(Type.Podcast);
        if (infors == null) {
            return ResponseEntity.badRequest().body("There is no podcast in the database");
        }
        List<PodcastResponse> podcasts = new ArrayList<>();
        for (Infor infor : infors) {
            if (infor.getEnable()) {
              PodcastResponse response = PodcastResponse.builder().id(infor.getId()).title(infor.getTitle())
                .audio(infor.getAudio()).desc(infor.getDescription()).img(infor.getImage()).build();
            podcasts.add(response);  
            }
        }
        return ResponseEntity.status(200).body(podcasts);
    }

    public ResponseEntity<?> updatePodcast(MultipartFile podcast,MultipartFile img, String title, String description) throws IOException{
        Infor infor = inforRepository.findByTitle(title);
        if (infor == null || !infor.getEnable()) {
            return ResponseEntity.badRequest().body("No Podcast with this title");
        }

        infor.setAudio(FileUploadUtil.saveFile(podcast.getOriginalFilename(), podcast));
        infor.setImage(FileUploadUtil.saveFile(img.getOriginalFilename(), img));
        infor.setDescription(description);
        inforRepository.save(infor);
        return ResponseEntity.status(200).body("Podcast updated");
    }

    public ResponseEntity<?> deletePodcast(String title){
        Infor infor = inforRepository.findByTitle(title);
        if (infor == null || !infor.getEnable()) {
            return ResponseEntity.badRequest().body("No Podcast with this title");
        }
        infor.setEnable(false);
        inforRepository.save(infor);
        return ResponseEntity.ok().body("Podcast Deleted ");
    }

}