package com.arido.manufacturing_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupAccessDTO {
    private String groupName;
    private String accessLevel;
}
