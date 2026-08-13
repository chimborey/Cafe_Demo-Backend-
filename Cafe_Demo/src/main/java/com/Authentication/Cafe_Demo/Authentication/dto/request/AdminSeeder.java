package com.Authentication.Cafe_Demo.Authentication.dto.request;


import com.Authentication.Cafe_Demo.Authentication.Enums.Roles;
import com.Authentication.Cafe_Demo.Authentication.model.Author;
import com.Authentication.Cafe_Demo.Authentication.repository.AuthorRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final AuthorRepo authorRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if (!authorRepo.existsByEmail("admincafedemo2026@gmail.com")){
            Author author = new Author();
            author.setFullName("admin");
            author.setEmail("admincafedemo2026@gmail.com");
            author.setPassword(passwordEncoder.encode("admincafedemo2026"));
            author.setRoles(Roles.ADMIN);
            authorRepo.save(author);

            System.out.println("create admin");

        }
    }
}
