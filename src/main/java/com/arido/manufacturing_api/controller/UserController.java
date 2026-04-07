package com.arido.manufacturing_api.controller;

import com.arido.manufacturing_api.dto.UserDTO;
import com.arido.manufacturing_api.dto.UserRegistrationDTO;
import com.arido.manufacturing_api.dto.UserWithAccessDTO;
import com.arido.manufacturing_api.exceptions.BadRequestException;
import com.arido.manufacturing_api.exceptions.ResourceNotFoundException;
import com.arido.manufacturing_api.model.User;
import com.arido.manufacturing_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<UserDTO> userOptional = userService.findByUsername(username);

        if (userOptional.isPresent()){
            return userService.findByUsername(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }else{
            throw  new ResourceNotFoundException("Username " + username + " no encontrado");
        }
    }

    @GetMapping("/with-access")
    public ResponseEntity<List<UserWithAccessDTO>> getUsersWithAccess() {
        return ResponseEntity.ok(userService.listAllUsersWithAccess());
    }
    @GetMapping("/with-access/{username}")
    public ResponseEntity<UserWithAccessDTO> getUserWithAccess(@PathVariable String username) {

        return userService.findUserWithAccessByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserWithAccessDTO> createUser(@Valid @RequestBody UserRegistrationDTO u) {
        return new ResponseEntity<>(userService.createUser(u), HttpStatus.CREATED);
    }

}
