package com.arido.manufacturing_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityGroupDTO {



    private Long groupId;

    @NotBlank(message = "El nombre del grupo de seguridad no puede estat vacio")
    @Size(max = 20, message = "El nombre del grupo de seguridad no debe exceder los 20 caracteres")
    private String name;

    @NotBlank(message = "la descripción no puede estar vacia")
    @Size(min = 5, max = 100, message = "La descripcion tiene que tener entre 5 y 100 caracteres")
    private String description;

}
