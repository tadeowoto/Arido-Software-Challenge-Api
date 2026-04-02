package com.arido.manufacturing_api.service.impl;

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

    @Override
    public List<SecurityGroup> listByUsername(String username) {
        return repository.findByUsername(username);
    }
}
