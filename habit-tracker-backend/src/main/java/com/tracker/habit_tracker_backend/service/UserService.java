package com.tracker.habit_tracker_backend.service;

import com.tracker.habit_tracker_backend.exceptions.ResourceNotFoundException;
import com.tracker.habit_tracker_backend.model.User;
import com.tracker.habit_tracker_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Create User
    public  User createUser(User user){
        //Save the user to the database
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users= userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found with id: " + id));
    }
}
