package com.example.bideew.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZEA1 {
    private String title;
    private String desc;
    private MultipartFile image;
}
