package com.example.userservice.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = ("users"))
public class User {

    @Id
    private String id;

    @NotBlank(message = "Brukernavn må minst ha 4 tegn")
    @Size(min = 4, message = "Brukernavn må ha minst 4 tegn")
    private String userName;

    @NotBlank(message = "Passord må minst ha 4 tegn")
    @Size(min = 4, message = "Passord må ha minst 4 tegn")
    private String password;

    @Size(min = 4, message = "Brukernavn må ha minst 4 tegn")
    private String email;

    private String role = "USER";

    public User() {
    };

    public User(String id, String userName, String password, String email, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
