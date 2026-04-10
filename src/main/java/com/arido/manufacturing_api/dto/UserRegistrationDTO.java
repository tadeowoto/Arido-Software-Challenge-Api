package com.arido.manufacturing_api.dto;

import com.arido.manufacturing_api.model.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotNull
    private UserStatus status;

    @NotNull
    @Size(min = 1, message = "Debe asignar al menos un grupo de seguridad")
    private List<GroupAccessRequestDTO> securityGroups;
}
