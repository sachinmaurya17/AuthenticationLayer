package com.example.authentication.repository;

import com.example.authentication.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

    @Query("SELECT u FROM UserDetails u  WHERE u.emailId = :emailId")
    Optional<UserDetails> findAllByEmailId(String emailId);

    @Query("SELECT u FROM UserDetails u  WHERE u.username = :username")
    Optional<UserDetails> findAllByUsername(String username);


}
