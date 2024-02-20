package com.example.authentication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mobile_number", nullable = false)
    private String phoneNumber;

    @Column(name = "name", nullable = false)
    private String name;
}




