package com.example.userservice.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.userservice.Model.User;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByUserName(String userName);

}
