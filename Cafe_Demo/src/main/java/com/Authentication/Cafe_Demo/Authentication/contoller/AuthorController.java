package com.Authentication.Cafe_Demo.Authentication.contoller;


import com.Authentication.Cafe_Demo.Authentication.dto.request.LoginRequest;
import com.Authentication.Cafe_Demo.Authentication.dto.request.RegisterRequest;
import com.Authentication.Cafe_Demo.Authentication.dto.response.LoginResponse;
import com.Authentication.Cafe_Demo.Authentication.dto.response.MessageResponse;
import com.Authentication.Cafe_Demo.Authentication.service.AuthorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/author")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthorController {
    private final AuthorService authorService;

//    =========================================register=========================
@PostMapping("/register")
public MessageResponse REGISTER(@Valid @RequestBody RegisterRequest request){
    return authorService.register(request);
}
//=========================================login=========================
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // ហៅប្រើប្រាស់ method login ពី Service មកដាក់បញ្ចូលទីនេះ
        LoginResponse response = authorService.login(request);

        return ResponseEntity.ok(response);
    }
}

