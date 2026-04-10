package com.arido.manufacturing_api.repository;

import com.arido.manufacturing_api.dto.UserWithAccessDTO;
import com.arido.manufacturing_api.model.UserSecurity;
import com.arido.manufacturing_api.model.UserSecurityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, UserSecurityKey> {
    List<UserSecurity> findByGroupGroupId(Long groupId);

    @Query("""
        SELECT us FROM UserSecurity us
        JOIN FETCH us.user
        JOIN FETCH us.group
        JOIN FETCH us.accessLevel
    """)
    List<UserSecurity> findAllWithDetails();

    @Query("""
    SELECT us FROM UserSecurity us
    JOIN FETCH us.user u
    JOIN FETCH us.group
    JOIN FETCH us.accessLevel
    WHERE u.username = :username""")
    List<UserSecurity> findByUsernameWithDetails(String username);
}
