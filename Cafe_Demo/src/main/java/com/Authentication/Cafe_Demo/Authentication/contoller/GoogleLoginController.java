package com.Authentication.Cafe_Demo.Authentication.contoller;

import com.Authentication.Cafe_Demo.Authentication.Enums.Roles;
import com.Authentication.Cafe_Demo.Authentication.model.Author;
import com.Authentication.Cafe_Demo.Authentication.repository.AuthorRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/author")
@RequiredArgsConstructor
public class GoogleLoginController {

    private final AuthorRepo authorRepo;

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
        String idTokenString = request.get("token");

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList("YOUR_GOOGLE_CLIENT_ID.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name");

                Optional<Author> existingUser = authorRepo.findByEmail(email);
                Author user;

                if (existingUser.isEmpty()) {
                    user = new Author();
                    user.setEmail(email);
                    user.setFullName(name != null ? name : "Google User");
                    user.setPassword("GOOGLE_LOGIN");
                    user.setEnabled(true);
                    user.setRoles(Roles.BUYER);
                    authorRepo.save(user);
                } else {
                    user = existingUser.get();
                }

                return ResponseEntity.ok(Map.of(
                        "id", user.getId(),
                        "email", user.getEmail(),
                        "role", user.getRoles().name(),
                        "businessType", "cafe"
                ));
            } else {
                return ResponseEntity.badRequest().body("Invalid Google Token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error verifying Google Token: " + e.getMessage());
        }
    }
}