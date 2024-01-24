package com.example.bideew.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bideew.model.Role;
import com.example.bideew.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email); 
    // @Transactional
    // @Modifying
    // @Query("UPDATE User a " +
    //         "SET a.enabled = TRUE WHERE a.email = ?1")
    // int enableUser(String email);

    List<User> findByRole(Role subscriber);
    
}
