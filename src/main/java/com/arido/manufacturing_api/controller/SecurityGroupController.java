package com.arido.manufacturing_api.controller;

import com.arido.manufacturing_api.dto.SecurityGroupDTO;
import com.arido.manufacturing_api.exceptions.ResourceNotFoundException;
import com.arido.manufacturing_api.service.SecurityGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class SecurityGroupController {

    private final SecurityGroupService groupService;


    @GetMapping
    public ResponseEntity<List<SecurityGroupDTO>> listAllSecurityGroups(){
        return ResponseEntity.ok(groupService.listAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<SecurityGroupDTO>> getSecurityGroups(@PathVariable String username) {

        List<SecurityGroupDTO> groups = groupService.listByUsername(username);
        if (groups.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron grupos para el usuario: " + username);
        }

        return ResponseEntity.ok(groupService.listByUsername(username));

    }
}
