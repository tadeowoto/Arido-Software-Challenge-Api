package com.arido.manufacturing_api.dto;

import com.arido.manufacturing_api.model.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

public class UserWithAccessDTO {

    private Long userId;
    private String username;
    private UserStatus status;
    private LocalDateTime createdAt;

    private List<UserGroupAccessDTO> groups;

}
