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
public class FlashBackDto {
    private String title;
    private String name;
    private String desc;
    private MultipartFile img;
}
