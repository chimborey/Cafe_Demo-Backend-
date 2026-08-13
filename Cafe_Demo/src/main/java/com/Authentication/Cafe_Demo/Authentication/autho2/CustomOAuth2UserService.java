package com.Authentication.Cafe_Demo.Authentication.autho2;

import com.Authentication.Cafe_Demo.Authentication.Enums.Roles;
import com.Authentication.Cafe_Demo.Authentication.model.Author;
import com.Authentication.Cafe_Demo.Authentication.repository.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AuthorRepo authorRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request)
            throws OAuth2AuthenticationException {

        // យកទិន្នន័យពី Google Server មកមុន
        OAuth2User oauthUser = super.loadUser(request);

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        // ឆែកមើលថាតើ Email នេះមាននៅក្នុង Database ហើយឬនៅ
        Optional<Author> existingUser = authorRepo.findByEmail(email);

        if (existingUser.isEmpty()) {
            // បើគ្មានទេ ធ្វើការបង្កើត Account ថ្មីដោយស្វ័យប្រវត្តិ (Auto Register)
            Author newUser = new Author();

            newUser.setEmail(email);
            newUser.setFullName(name != null ? name : "Google User");

            // Google user មិនមាន password ទេ ដូច្នេះកំណត់តម្លៃ placeholder ទុក
            newUser.setPassword("GOOGLE_LOGIN");

            newUser.setPhoneNumber("");
            newUser.setAddress("");
            newUser.setCountry("");

            newUser.setEnabled(true);
            newUser.setRoles(Roles.BUYER);

            authorRepo.save(newUser);
        }

        return oauthUser;
    }
}