package com.example.BookStore.Controller.admin;

import com.example.BookStore.entity.Author;
import com.example.BookStore.entity.Category;
import com.example.BookStore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin/author")
    public String showAllAuthors(Model model) {
        List<Author> authorList = authorService.getAllAuthors();
        model.addAttribute("authors", authorList);
        return "admin/author/list";
    }
    @GetMapping("/admin/author/add")
    public String showAddAuthorForm(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        return "admin/author/new_author";
    }
    @PostMapping("/admin/author/save")
    public String saveAuthor(@ModelAttribute("author") Author author) {
        authorService.save(author);
        return "redirect:/admin/author";
    }
    @GetMapping("/admin/author/edit/{id}")
    public String showEditAuthorPage(@PathVariable("id") Integer id, Model model){
        Author author = authorService.get(id);
        if(author == null){
            return "notfound";
        }else {

            model.addAttribute("author",author);
            model.addAttribute("author",author);
            return "admin/author/edit";
        }
    }
    @GetMapping("/admin/author/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id){
        Author author = authorService.get(id);
        if(author == null){
            return "notfound";
        }else{
            authorService.delete(id);
            return "redirect:/admin/author";
        }
    }
}
