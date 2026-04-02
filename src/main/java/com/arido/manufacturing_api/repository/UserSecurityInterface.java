package com.arido.manufacturing_api.repository;

import com.arido.manufacturing_api.model.UserSecurity;
import com.arido.manufacturing_api.model.UserSecurityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSecurityInterface extends JpaRepository<UserSecurity, UserSecurityKey> {

    List<UserSecurity> findByGroupId(Long groupId);
}
