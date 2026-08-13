package com.Authentication.Cafe_Demo.Authentication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageResponse implements Serializable {

    private Long id;
    private boolean success;
    private String message;
}
