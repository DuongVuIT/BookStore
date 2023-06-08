package com.example.BookStore.repository;

import com.example.BookStore.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepository extends JpaRepository<User,Long> {
    @Query("SELECT U FROM User U WHERE U.username = ?1")
    User findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role (makh, maquyen)" + "VALUES (?1, ?2)", nativeQuery = true)
    void addRoleToUser(Long makh, Long maquyen);

    @Query("SELECT u.makh FROM User u WHERE u.username = ?1")
    Long getUserIdByUsername(String username);

    @Query (value = "SELECT r.tenquyen FROM role r INNER JOIN user_role ur "+"ON r.maquyen=ur.maquyen WHERE ur.makh = ?1", nativeQuery = true)
    String[] getRolesOfUser(Long makh);
}
