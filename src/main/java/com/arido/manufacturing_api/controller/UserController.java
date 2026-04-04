package com.arido.manufacturing_api.controller;

import com.arido.manufacturing_api.dto.UserDTO;
import com.arido.manufacturing_api.dto.UserRegistrationDTO;
import com.arido.manufacturing_api.model.User;
import com.arido.manufacturing_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable  String username){
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationDTO u){
        UserDTO userCreated = userService.createUser(u);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

}
