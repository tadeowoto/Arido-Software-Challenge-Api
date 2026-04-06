package com.arido.manufacturing_api.repository;

import com.arido.manufacturing_api.model.AccessLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel,Long> {}
