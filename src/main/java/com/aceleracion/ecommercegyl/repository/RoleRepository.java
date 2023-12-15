package com.aceleracion.ecommercegyl.repository;

import com.aceleracion.ecommercegyl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}
