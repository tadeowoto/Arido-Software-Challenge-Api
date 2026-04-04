package com.arido.manufacturing_api.service;

import com.arido.manufacturing_api.dto.UserDTO;
import com.arido.manufacturing_api.dto.UserRegistrationDTO;
import com.arido.manufacturing_api.dto.UserWithAccessDTO;
import com.arido.manufacturing_api.model.User;
import com.arido.manufacturing_api.model.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserRegistrationDTO u);

    List<UserDTO> listAllUsers();

    Optional<UserDTO> findByUsername(String username);

    List<UserWithAccessDTO> listAllUsersWithAccess();

    UserDTO changeUserState(Long userId, UserStatus newStatus);
}
