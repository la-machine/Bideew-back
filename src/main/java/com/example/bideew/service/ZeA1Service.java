package com.example.bideew.service;

import java.io.IOException;
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
public class ZeA1Service {
    
    private final InforRepository inforRepository;

    public ResponseEntity<?> createA1(MultipartFile img, String title, String description) throws IOException{
        Infor infor = inforRepository.findByEnableAndTitle(true, title);
        if (infor != null) {
            if(infor.getEvenType() == Type.ZeA1){
                return ResponseEntity.badRequest().body("Event with this title already exist");
            }
        }
        Infor saveA1 = Infor.builder().title(title).description(description)
            .evenType(Type.ZeA1).image(FileUploadUtil.saveFile(img.getOriginalFilename(), img))
            .build();
        
        inforRepository.save(saveA1);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAllZeA1(){
        List<Infor> zeA1s = inforRepository.findByEnableAndEvenType(true, Type.ZeA1);
        return ResponseEntity.ok().body(zeA1s);

    }
}
