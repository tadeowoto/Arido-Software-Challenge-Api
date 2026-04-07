package com.arido.manufacturing_api.service.impl;

import com.arido.manufacturing_api.dto.UserDTO;
import com.arido.manufacturing_api.dto.UserGroupAccessDTO;
import com.arido.manufacturing_api.dto.UserRegistrationDTO;
import com.arido.manufacturing_api.dto.UserWithAccessDTO;
import com.arido.manufacturing_api.exceptions.BadRequestException;
import com.arido.manufacturing_api.exceptions.ResourceNotFoundException;
import com.arido.manufacturing_api.mapper.UserMapper;
import com.arido.manufacturing_api.model.*;
import com.arido.manufacturing_api.repository.AccessLevelRepository;
import com.arido.manufacturing_api.repository.SecurityGroupRepository;
import com.arido.manufacturing_api.repository.UserRepository;
import com.arido.manufacturing_api.repository.UserSecurityRepository;
import com.arido.manufacturing_api.service.UserService;
import jakarta.persistence.EntityNotFoundException;
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
    private final SecurityGroupRepository groupRepository;
    private final AccessLevelRepository accessLevelRepository;

    @Override
    @Transactional
    public UserWithAccessDTO createUser(UserRegistrationDTO u) {

        User user = new User();
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setStatus(u.getStatus());

        if (repository.existsByUsername(u.getUsername())) {
            throw new BadRequestException("El nombre de usuario '" + u.getUsername() + "' ya está en uso");
        }

        User savedUser = repository.save(user);


        SecurityGroup group = groupRepository.findById(u.getGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo no encontrado"));

        AccessLevel level = accessLevelRepository.findById(u.getAccessLevelId())
                .orElseThrow(() -> new ResourceNotFoundException("Nivel de acceso no encontrado"));

        UserSecurity userSecurity = new UserSecurity();

        UserSecurityKey key = new UserSecurityKey(
                savedUser.getUserId(),
                group.getGroupId()
        );

        userSecurity.setId(key);
        userSecurity.setUser(savedUser);
        userSecurity.setGroup(group);
        userSecurity.setAccessLevel(level);

        userSecurityRepository.save(userSecurity);

        List<UserGroupAccessDTO> groups = List.of(
                new UserGroupAccessDTO(
                        group.getName(),
                        level.getName()
                )
        );

        return new UserWithAccessDTO(
                savedUser.getUserId(),
                savedUser.getUsername(),
                savedUser.getStatus(),
                savedUser.getCreatedAt(),
                groups
        );
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
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con el id " + userId + " no encontrado"));

        if (newStatus == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo");
        }

        user.setStatus(newStatus);
        User updatedUser = repository.save(user);

        return userMapper.toDTO(updatedUser);
    }


    public Optional<UserWithAccessDTO> findUserWithAccessByUsername(String username) {

        List<UserSecurity> relations = userSecurityRepository.findByUsernameWithDetails(username);
        if (relations.isEmpty()) {
            return Optional.empty();
        }

        User user = relations.get(0).getUser();

        List<UserGroupAccessDTO> groups = relations.stream()
                .map(rel -> new UserGroupAccessDTO(
                        rel.getGroup().getName(),
                        rel.getAccessLevel().getName()
                ))
                .toList();
        
        UserWithAccessDTO dto = new UserWithAccessDTO(
                user.getUserId(),
                user.getUsername(),
                user.getStatus(),
                user.getCreatedAt(),
                groups
        );

        return Optional.of(dto);
    }

}
