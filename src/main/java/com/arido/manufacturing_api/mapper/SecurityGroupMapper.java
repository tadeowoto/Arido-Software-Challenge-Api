package com.arido.manufacturing_api.mapper;


import com.arido.manufacturing_api.dto.SecurityGroupDTO;
import com.arido.manufacturing_api.model.SecurityGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityGroupMapper {

    private final ModelMapper modelMapper;

    public SecurityGroup toEntity(SecurityGroupDTO securityGroupDTO){
        return modelMapper.map(securityGroupDTO, SecurityGroup.class);
    }

    public SecurityGroupDTO toDTO(SecurityGroup securityGroup){
        return modelMapper.map(securityGroup, SecurityGroupDTO.class);
    }
}
