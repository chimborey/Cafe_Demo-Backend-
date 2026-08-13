package com.Authentication.Cafe_Demo.Authentication.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {

    private Long id;

    @NotBlank(message = "fullName is request")
    private String fullName;

    @NotBlank(message = "Email is request")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is request")
    private String password;

    @NotBlank(message = "Phone is request")
    @Pattern(regexp = "^\\d{3}\\d{3}\\d{4}$", message = "Phone number must match XXX-XXX-XXXX")
    private String phoneNumber;

    @NotBlank(message = "Address is request")
    private String address;

    @NotBlank(message = "Country is request")
    private String country;
}
