package com.example.userservice.Service;

import com.example.userservice.Model.User;
import com.example.userservice.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bruker ikke funnet: " + username));

        // Ensure role is not null
        String role = user.getRole();
        if (role == null || role.isEmpty()) {
            throw new UsernameNotFoundException("Bruker har ingen rolle: " + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(role) // Ensure role is not null
                .build();
    }

}
