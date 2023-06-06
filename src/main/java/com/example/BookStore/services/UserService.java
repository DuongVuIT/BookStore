package com.example.BookStore.services;

import com.example.BookStore.dto.UserDto;
import com.example.BookStore.entity.User;
import com.example.BookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService  {
    void saveUser(UserDto userDto);

    User findByUsername(String username);

    List<UserDto> findAllUsers();
}
