package com.example.BookStore.repository;

import com.example.BookStore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r.maquyen FROM Role r WHERE r.tenquyen = ?1")
    Long getRoleIdByName(String roleName);
}
