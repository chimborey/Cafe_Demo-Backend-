package com.Authentication.Cafe_Demo.Authentication.model;


import com.Authentication.Cafe_Demo.Authentication.Enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Authentication")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName", length = 300)
    private String fullName;

    @Column(name = "phoneNumber", length = 3000)
    private String phoneNumber;

    @Column(name = "email", length = 300, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 300)
    private String password;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "country", length = 300)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 300)
    private Roles roles;

    private Boolean enabled = false;
}
