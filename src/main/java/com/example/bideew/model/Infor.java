package com.example.bideew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Infor {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Type evenType;
    private String event;
    private String audio;
    private String video;
    private String image;
    @Builder.Default
    private Boolean enable=true;
    // private String downloadurl;
}
