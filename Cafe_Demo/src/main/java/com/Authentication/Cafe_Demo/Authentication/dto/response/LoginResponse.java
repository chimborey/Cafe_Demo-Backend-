package com.Authentication.Cafe_Demo.Authentication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {

    private String token;
    private Long id;
    private String email;
    private String password;
    private String role;
    private String businessType;
}
