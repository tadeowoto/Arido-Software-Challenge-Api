package com.arido.manufacturing_api.repository;

import com.arido.manufacturing_api.model.SecurityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecurityGroupRepository extends JpaRepository<SecurityGroup, Long> {

    @Query("SELECT us.group FROM UserSecurity us WHERE us.user.username = :username")
    List<SecurityGroup> findByUsername(@Param("username") String username);
}
