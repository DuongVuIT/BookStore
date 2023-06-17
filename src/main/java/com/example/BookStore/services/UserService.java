package com.example.BookStore.services;

import com.example.BookStore.entity.Category;
import com.example.BookStore.entity.Product;
import com.example.BookStore.entity.User;
import com.example.BookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }
    public User get(Integer id){
        return userRepository.findById(id).get();
    }
    public void delete(Integer id){
        userRepository.deleteById(id);
    }
}
