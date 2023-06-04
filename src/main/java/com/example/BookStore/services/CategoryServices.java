package com.example.BookStore.services;

import com.example.BookStore.entity.Category;
import com.example.BookStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getAllCate(){
        return categoryRepository.findAll();
    }
    public void save(Category category){
        categoryRepository.save(category);
    }
    public Category get(Integer id){
        return categoryRepository.findById(id).get();
    }
    public void delete(Integer id){
        categoryRepository.deleteById(id);
    }
}
