package com.example.userservice.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.userservice.Model.User;
import com.example.userservice.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Legg til bruker
    @PostMapping()
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    // Hent alle brukere
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // http://localhost:8080/api/user
    // Header Authorization: Bearer <DIN_JWT_TOKEN_HER>
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    // Slett bruker etter ID
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
    }
}
