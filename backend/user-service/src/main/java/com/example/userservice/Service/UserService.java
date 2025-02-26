package com.example.userservice.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.userservice.Model.User;
import com.example.userservice.Repository.UserRepository;
import com.example.userservice.Exception.DuplicateResourceException;
import com.example.userservice.Exception.ResourceNotFoundException;

import java.util.List;
import org.springframework.http.ResponseEntity;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Brukerregistrering med kryptert passord
    public void addUser(User user) {
        if (userRepository.existsByUserName(user.getUserName()) || userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException("En bruker med samme brukernavn eller e-post eksisterer allerede.");
        }

        // 🚀 Hash passordet med BCrypt
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    // ✅ Hent alle brukere
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Hent bruker etter ID
    public ResponseEntity<User> getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fant ikke bruker med ID: " + id));
        return ResponseEntity.ok().body(user); // ✅ Riktig returtype
    }

    // ✅ Slett bruker etter ID
    public void deleteUserById(String id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fant ikke bruker med ID: " + id));
        userRepository.deleteById(id);
    }
}
