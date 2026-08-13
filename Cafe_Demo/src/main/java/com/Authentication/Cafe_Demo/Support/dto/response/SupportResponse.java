package com.Authentication.Cafe_Demo.Support.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportResponse implements Serializable {

    private Long id;
    private String image;
    private String fullName;
    private String email;
    private String subjects;
    private String description;
    private String status;
}
