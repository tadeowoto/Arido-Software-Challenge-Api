package com.arido.manufacturing_api.dto;

import com.arido.manufacturing_api.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWithAccessDTO {

    private Long userId;
    private String username;
    private UserStatus status;
    private LocalDateTime createdAt;

    private List<UserGroupAccessDTO> groups;

}
