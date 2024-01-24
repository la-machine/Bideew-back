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
public class ZeInsideRequest {
    private String title;
    private String desc;
    private MultipartFile image;
    private MultipartFile video;
    private MultipartFile audio;
}
