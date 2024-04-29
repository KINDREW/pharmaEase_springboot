package com.pharmaease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pharmaease.dto.UserDto;
import com.pharmaease.dto.LoginDto;
import com.pharmaease.entity.User;
import com.pharmaease.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	 @Autowired
	    private UserService userService;
	    
	    @PostMapping("/register")
	    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
	        User user = userService.registerUser(userDto);
	        return new ResponseEntity<>(user, HttpStatus.CREATED);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<User> loginUser(@RequestBody LoginDto loginDto) {
	        User user = userService.loginUser(loginDto);
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    }
}
