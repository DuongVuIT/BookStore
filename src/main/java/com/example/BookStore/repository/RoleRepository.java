package com.example.BookStore.repository;

import com.example.BookStore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByTenquyen(String tenquyen);
}
