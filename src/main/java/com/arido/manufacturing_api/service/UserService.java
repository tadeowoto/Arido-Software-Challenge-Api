package com.arido.manufacturing_api.service;

import com.arido.manufacturing_api.model.User;
import com.arido.manufacturing_api.model.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User u);

    List<User> listAllUsers();

    Optional<User> findByUsername(String username);

    User changeUserState(Long userId, UserStatus newStatus);
}
