package com.arido.manufacturing_api.service.impl;

import com.arido.manufacturing_api.dto.UserDTO;
import com.arido.manufacturing_api.dto.UserGroupAccessDTO;
import com.arido.manufacturing_api.dto.UserRegistrationDTO;
import com.arido.manufacturing_api.dto.UserWithAccessDTO;
import com.arido.manufacturing_api.mapper.UserMapper;
import com.arido.manufacturing_api.model.User;
import com.arido.manufacturing_api.model.UserSecurity;
import com.arido.manufacturing_api.model.UserStatus;
import com.arido.manufacturing_api.repository.UserRepository;
import com.arido.manufacturing_api.repository.UserSecurityRepository;
import com.arido.manufacturing_api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserSecurityRepository userSecurityRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserRegistrationDTO u) {
        User user = userMapper.toEntity(u);
        User savedUser = repository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(userMapper::toDTO);
    }

    @Override
    public List<UserWithAccessDTO> listAllUsersWithAccess() {

        List<UserSecurity> relations = userSecurityRepository.findAllWithDetails();


        Map<User, List<UserSecurity>> grouped =
                relations.stream()
                        .collect(Collectors.groupingBy(UserSecurity::getUser));


        return grouped.entrySet().stream().map(entry -> {
            User user = entry.getKey();
            List<UserGroupAccessDTO> groups = entry.getValue().stream()
                    .map(rel -> new UserGroupAccessDTO(
                            rel.getGroup().getName(),
                            rel.getAccessLevel().getName()
                    ))
                    .toList();

            return new UserWithAccessDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getStatus(),
                    user.getCreatedAt(),
                    groups
            );

        }).toList();
    }


    @Override
    @Transactional
    public UserDTO changeUserState(Long userId, UserStatus newStatus) {

        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario con el id " + userId + " no encontrado"));

        if (newStatus == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }

        user.setStatus(newStatus);
        User updatedUser = repository.save(user);

        return userMapper.toDTO(updatedUser);
    }
}
