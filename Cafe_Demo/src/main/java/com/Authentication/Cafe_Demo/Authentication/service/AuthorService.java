package com.Authentication.Cafe_Demo.Authentication.service;


import com.Authentication.Cafe_Demo.Authentication.Enums.Roles;
import com.Authentication.Cafe_Demo.Authentication.dto.request.LoginRequest;
import com.Authentication.Cafe_Demo.Authentication.dto.request.RegisterRequest;
import com.Authentication.Cafe_Demo.Authentication.dto.response.LoginResponse;
import com.Authentication.Cafe_Demo.Authentication.dto.response.MessageResponse;
import com.Authentication.Cafe_Demo.Authentication.jwt.JWTService;
import com.Authentication.Cafe_Demo.Authentication.model.Author;
import com.Authentication.Cafe_Demo.Authentication.repository.AuthorRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JWTService jwtService;

//    =====================================register=======================
    public MessageResponse register(RegisterRequest request){
//        find by email in database
        try {
            if (authorRepo.existsByEmail(request.getEmail())){
                return new MessageResponse(
                        null,
                        false,
                        "Email already exists"
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Author user = new Author();
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setCountry(request.getCountry());
        user.setRoles(Roles.BUYER);
        user.setEnabled(false);

        authorRepo.save(user);

        Random random = new Random();
        int otpCode = 100000 + random.nextInt(900000);

        String subject = "Your OTP Verification Code";
        String body = "Hello " + request.getFullName() + ",\n\nThank you for registering. Please use the following OTP code to verify your account:";

        emailService.sendOtp(request.getEmail(), otpCode, body, subject);

        return new MessageResponse(null, true, "Successfully registered. Please check your email.");
    }
    //    =======================================login===========================
    public LoginResponse login(LoginRequest request){

        // ១. ស្វែងរក User តាមរយៈ Email (បើគ្មាន បោះ Exception ចេញភ្លាម)
        Author user = authorRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // ២. ផ្ទៀងផ្ទាត់ Password ជាមួយ BCrypt
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // ៣. 🛑 បង្កើត Token ដោយប្រើ Email របស់ User
        String token = jwtService.generatedKey(user.getEmail());

        // ៤. បើត្រឹមត្រូវទាំងអស់ ត្រឡប់ទិន្នន័យ និង Token (ប្រសិនបើមាន JWT) ទៅកាន់ Client
        return new LoginResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().name(),
                "cafe"
        );
    }
}