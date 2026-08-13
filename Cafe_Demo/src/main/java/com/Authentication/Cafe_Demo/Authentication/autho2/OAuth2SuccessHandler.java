package com.Authentication.Cafe_Demo.Authentication.autho2;
import com.Authentication.Cafe_Demo.Authentication.jwt.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTService jwtService;
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException {

        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");
        String token = jwtService.generatedKey(email); // បង្កើត JWT Token សម្រាប់ User
        System.out.println("EMAIL = " + email);
        System.out.println("NAME = " + name);
        System.out.println("PICTURE = " + picture);

        response.sendRedirect(
                "http://localhost:5173/oauth-success" +
                        "?email=" + email +
                        "&name=" + name +
                        "&picture=" + picture +
                        "&token=" + token
        );
    }
}
