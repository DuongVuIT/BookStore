package com.example.BookStore.services;

import com.example.BookStore.entity.Author;
import com.example.BookStore.entity.Category;
import com.example.BookStore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public void save(Author author){
        authorRepository.save(author);
    }
    public Author get(Integer id){
        return authorRepository.findById(id).get();
    }
    public void delete(Integer id){
        authorRepository.deleteById(id);
    }

}
