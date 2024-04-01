package com.example.bideew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bideew.model.Infor;
import com.example.bideew.model.Type;

public interface InforRepository extends JpaRepository<Infor, Long> {
    Infor findByEnableAndTitle(Boolean enable,String title);
    List<Infor> findByEnableAndEvenType(Boolean enable, Type type);
}
