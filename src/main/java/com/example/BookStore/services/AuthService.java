package com.example.BookStore.services;

import com.example.BookStore.entity.User;
import com.example.BookStore.repository.AuthRepository;
import com.example.BookStore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void save(User user) {
        authRepository.save(user);
        Long makh = authRepository.getUserIdByUsername(user.getUsername());
        Long maquyen = roleRepository.getRoleIdByName("User");
        if (maquyen != 0 && makh != 0) {
            authRepository.addRoleToUser(makh, maquyen);
        }

    }
}
