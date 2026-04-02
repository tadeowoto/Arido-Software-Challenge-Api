package com.arido.manufacturing_api.service.impl;

import com.arido.manufacturing_api.model.User;
import com.arido.manufacturing_api.model.UserStatus;
import com.arido.manufacturing_api.repository.UserRepository;
import com.arido.manufacturing_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User createUser(User u) {
        return null;
    }

    @Override
    public List<User> listAllUsers() {
        return List.of();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public User changeUserState(Long userId, UserStatus newStatus) {
        return null;
    }
}
