package com.example.bideew.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZeInside {
    private String title;
    private String desc;
    private String img;
    private String audio;
    private String video;
}
