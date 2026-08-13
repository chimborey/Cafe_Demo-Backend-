package com.Authentication.Cafe_Demo.Support.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "support")
@Data
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "fullName", length = 300, nullable = false, unique = true)
    private String fullName;

    @Column(name = "email", length = 300, nullable = false, unique = true)
    private String email;

    @Column(name = "subjects", length = 300, nullable = false, unique = true)
    private String subjects;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;


}
