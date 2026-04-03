package com.arido.manufacturing_api.service.impl;

import com.arido.manufacturing_api.dto.SecurityGroupDTO;
import com.arido.manufacturing_api.mapper.SecurityGroupMapper;
import com.arido.manufacturing_api.model.SecurityGroup;
import com.arido.manufacturing_api.repository.SecurityGroupRepository;
import com.arido.manufacturing_api.service.SecurityGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityGroupServiceImpl implements SecurityGroupService {

    private final SecurityGroupRepository repository;
    private final SecurityGroupMapper securityGroupMapper;

    @Override
    public List<SecurityGroupDTO> listByUsername(String username) {
        List<SecurityGroup> groups = repository.findByUsername(username);

        return groups.stream()
                .map(securityGroupMapper::toDTO)
                .toList();
    }
}
