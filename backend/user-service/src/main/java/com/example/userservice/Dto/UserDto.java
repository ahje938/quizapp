package com.example.userservice.Dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;

@Document(collection = ("users"))
public class UserDto {
    
    @Id
    private String id;
    @NotNull
    public String userName;
    @NotNull
    public String email;

    public UserDto(){};

    public UserDto(String id, String userName, String email){
        this.id = id;
        this.userName = userName;
        this.email = email;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }



    
}
