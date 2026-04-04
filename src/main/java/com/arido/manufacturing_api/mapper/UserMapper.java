package com.arido.manufacturing_api.mapper;

import com.arido.manufacturing_api.dto.UserDTO;
import com.arido.manufacturing_api.dto.UserRegistrationDTO;
import com.arido.manufacturing_api.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO toDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserRegistrationDTO userRDTO){
        return modelMapper.map(userRDTO, User.class);
    }
}
