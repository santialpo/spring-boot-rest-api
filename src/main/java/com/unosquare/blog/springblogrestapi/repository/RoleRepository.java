package com.unosquare.blog.springblogrestapi.repository;

import com.unosquare.blog.springblogrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
