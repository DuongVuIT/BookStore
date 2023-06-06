package com.example.BookStore.services;

import com.example.BookStore.dto.UserDto;
import com.example.BookStore.entity.Role;
import com.example.BookStore.entity.User;
import com.example.BookStore.repository.RoleRepository;
import com.example.BookStore.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setDiachi(userDto.getDiachi());
        user.setEmail(userDto.getEmail());
        user.setFullname(userDto.getFullname());
        user.setSdt(userDto.getSdt());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByTenquyen("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getFullname().split("");
        userDto.setDiachi(user.getDiachi());
        userDto.setFullname(user.getFullname());
        userDto.setEmail(user.getEmail());
        userDto.setSdt(user.getSdt());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setTenquyen("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}
