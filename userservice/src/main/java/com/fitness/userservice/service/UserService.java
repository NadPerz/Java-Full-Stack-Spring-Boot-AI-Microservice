package com.fitness.userservice.service;


import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeInvisAnnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    public UserResponse register(@Valid RegisterRequest  request) {

        if(repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exist");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User SavedUser= repository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(SavedUser.getId());
        userResponse.setPassword(SavedUser.getPassword());
        userResponse.setPassword(SavedUser.getEmail());
        userResponse.setFirstName(SavedUser.getFirstName());
        userResponse.setLastName(SavedUser.getLastName());
        userResponse.setCreatedAt(SavedUser.getCreatedAt());
        userResponse.setUpdatedAt(SavedUser.getUpdatedAt());

        return UserResponse;


    }
}
