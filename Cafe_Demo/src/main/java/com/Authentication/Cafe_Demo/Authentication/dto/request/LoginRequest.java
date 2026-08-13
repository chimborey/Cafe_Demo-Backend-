package com.Authentication.Cafe_Demo.Authentication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class LoginRequest implements Serializable {

    private Long id;
    private String email;
    private String password;
}
