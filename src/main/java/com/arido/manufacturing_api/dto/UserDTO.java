package com.arido.manufacturing_api.dto;


import com.arido.manufacturing_api.model.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Long userId;

    @NotBlank(message = "El nombre del usuario no puede estar vacio")
    @Size(max = 20, message = "El username no debe exceder los 20 caracteres")
    private String username;

    @NotNull(message = "El status no puede ser nulo")
    private UserStatus status;


    private LocalDateTime createdAt;


}
