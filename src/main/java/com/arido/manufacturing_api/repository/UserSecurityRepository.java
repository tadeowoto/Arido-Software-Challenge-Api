package com.arido.manufacturing_api.repository;

import com.arido.manufacturing_api.model.UserSecurity;
import com.arido.manufacturing_api.model.UserSecurityKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, UserSecurityKey> {
    List<UserSecurity> findByGroupId(Long groupId);
}
