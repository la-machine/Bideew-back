package com.example.bideew.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bideew.model.Infor;
import com.example.bideew.model.Type;

public interface InforRepository extends JpaRepository<Infor, Long> {
    Infor findByTitle(String title);
    List<Infor> findByEvenType(Type type);
}
