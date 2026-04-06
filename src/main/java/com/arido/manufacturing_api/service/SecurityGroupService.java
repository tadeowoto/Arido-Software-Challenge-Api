package com.arido.manufacturing_api.service;


import com.arido.manufacturing_api.dto.SecurityGroupDTO;
import com.arido.manufacturing_api.model.SecurityGroup;

import java.util.List;

public interface SecurityGroupService {

    List<SecurityGroupDTO> listByUsername(String username);

}
