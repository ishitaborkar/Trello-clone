package com.example.trelloclone.repository;


import com.example.trelloclone.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Custom query method to find a user by their name
    Optional<User> findByName(String name);
    

    
}
