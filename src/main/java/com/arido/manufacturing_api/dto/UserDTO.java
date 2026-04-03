package com.arido.manufacturing_api.dto;


import com.arido.manufacturing_api.model.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank
    private Long userId;


    private String username;


    private UserStatus status;


    private LocalDateTime createdAt;


}
