package com.example.bideew.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bideew.model.Infor;
import com.example.bideew.model.Type;
import com.example.bideew.repository.InforRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BigeventService {
    
    private final InforRepository inforRepository;

    public ResponseEntity<?> getAllEvents(){
        List<Infor> events = inforRepository.findByEvenType(Type.BigEvent);
        Iterator<Infor> iterator = events.iterator();
        List<Infor> filteredEvents = new ArrayList<>();
        while (iterator.hasNext()) {
            Infor infor = iterator.next();
            if (infor.getEnable()) {
                filteredEvents.add(infor);
            }
        }
       
        return ResponseEntity.ok().body(filteredEvents);
    }

    public ResponseEntity<?> addEvent(MultipartFile img, String title, String description) throws IOException{
        Infor tempInfor = inforRepository.findByTitle(title);
        if(tempInfor != null){
            return ResponseEntity.status(400).body("Event with this title already exist");
        }
        Infor infor = Infor.builder().enable(true).title(title).description(description).evenType(Type.BigEvent)
        .image(FileUploadUtil.saveFile(img.getOriginalFilename(), img)).build();
        inforRepository.save(infor);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> updatePodcast(MultipartFile img, String title, String description) throws IOException{
        Infor infor = inforRepository.findByTitle(title);
        if (infor == null || !infor.getEnable()) {
            return ResponseEntity.badRequest().body("No Podcast with this title");
        }

        infor.setImage(FileUploadUtil.saveFile(img.getOriginalFilename(), img));
        infor.setDescription(description);
        inforRepository.save(infor);
        return ResponseEntity.status(200).body("Podcast updated");
    }
}