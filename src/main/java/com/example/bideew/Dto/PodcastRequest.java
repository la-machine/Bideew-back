package com.example.bideew.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PodcastRequest {
    private String title;
    private String desc;
    private MultipartFile img;
    private MultipartFile audio;
}
