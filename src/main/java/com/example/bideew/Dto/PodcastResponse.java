package com.example.bideew.Dto;

// import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PodcastResponse {
    private Long id;
    private String title;
    private String desc;
    private String img;
    private String audio;
}
