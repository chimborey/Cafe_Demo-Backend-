package com.Authentication.Cafe_Demo.Support.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SupportRequest implements Serializable {

    private String image;
    private String fullName;
    private String email;
    private String subjects;
    private String description;
    private String status;
}
