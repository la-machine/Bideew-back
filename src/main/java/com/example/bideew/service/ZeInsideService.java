package com.example.bideew.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bideew.Dto.ZeInside;
import com.example.bideew.model.Infor;
import com.example.bideew.model.Type;
import com.example.bideew.repository.InforRepository;

@Service
public class ZeInsideService {
    @Autowired
    private InforRepository inforRepository;

    public ResponseEntity<?> createInterview(MultipartFile video, MultipartFile audio,MultipartFile img,
         String title, String desc) throws IOException{
        Infor inforExist = inforRepository.findByEnableAndTitle(true, title);
        if (inforExist != null) {
            return ResponseEntity.badRequest().body("Information with this title already exist");
        }
        Infor infor = Infor.builder().title(title).description(desc).evenType(Type.ZeInside)
        .audio(FileUploadUtil.saveFile(audio.getOriginalFilename(), audio))
        .video(FileUploadUtil.saveFile(video.getOriginalFilename(), video))
        .image(FileUploadUtil.saveFile(img.getOriginalFilename(), img)).build();
        inforRepository.save(infor);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getInterview(String title) throws IOException{
        Infor inforExist = inforRepository.findByEnableAndTitle(true, title);
        if (inforExist == null) {
            return ResponseEntity.badRequest().body("There is no inerview recorded with this title");
        }
        ZeInside inside = ZeInside.builder().title(inforExist.getTitle())
            .desc(inforExist.getDescription()).audio(inforExist.getAudio()).img(inforExist.getImage()).video(inforExist.getVideo()).build();
        return ResponseEntity.status(200).body(inside);
    }

    public ResponseEntity<?> getAllInterviews() throws IOException{
        List<Infor> infors = inforRepository.findByEnableAndEvenType(true, Type.ZeInside);
        List<ZeInside> interviews = new ArrayList<>();
        for (Infor infor : infors) {
            ZeInside inside = ZeInside.builder().title(infor.getTitle())
            .desc(infor.getDescription()).audio(infor.getAudio()).img(infor.getImage()).video(infor.getVideo()).build();
            interviews.add(inside);

        }

        return ResponseEntity.status(200).body(interviews);
    }

    public ResponseEntity<?> updateInterview(MultipartFile video, MultipartFile audio, MultipartFile image,
            String title, String desc) throws IOException {
        Infor infor = inforRepository.findByEnableAndTitle(true,title);
        if (infor == null) {
            return ResponseEntity.badRequest().body("No Podcast with this title");
        }

        infor.setAudio(FileUploadUtil.saveFile(audio.getOriginalFilename(), audio));
        infor.setImage(FileUploadUtil.saveFile(image.getOriginalFilename(), image));
        infor.setVideo(FileUploadUtil.saveFile(video.getOriginalFilename(), video));
        infor.setDescription(desc);
        inforRepository.save(infor);
        return ResponseEntity.ok().body("interview " + infor.getTitle() + " updated successfully");
    }
    
    
}