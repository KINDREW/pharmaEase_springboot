package com.pharmaease.services;

import com.pharmaease.dto.UserDto;
import com.pharmaease.dto.LoginDto;
import com.pharmaease.entity.User;
import com.pharmaease.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) {
        // Validate user input (e.g., check for required fields, email format)
        if (userDto.getUsername() == null || userDto.getEmail() == null || userDto.getPassword() == null) {
            throw new IllegalArgumentException("Username, email, and password are required.");
        }

        // Check if email is already registered
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        // Hash the user's password before storing it
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        // Create a new user entity and populate its properties
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(hashedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }

    public User loginUser(LoginDto loginDto) {
        // Retrieve user by email (assuming email is unique)
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                // Authentication successful
                return user;
            } else {
                // Incorrect password
            	throw new IllegalArgumentException("Incorrect password");
            }
        } else {
            // User not found
        	throw new IllegalArgumentException("User not found");
        }
    }

}
