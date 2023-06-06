package com.example.BookStore.Controller.admin;

import com.example.BookStore.entity.Category;
import com.example.BookStore.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
   @Autowired
    private CategoryServices categoryServices;
   @GetMapping
    public String showAllCate(Model model){
       List<Category> categories = categoryServices.getAllCate();
       model.addAttribute("categories",categories);
       return "admin/category/list";
   }
    @GetMapping("/new")
    public String showNewCatePage(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "admin/category/new_cate";
    }
    @PostMapping("/save")
    public String saveCate(@ModelAttribute("category") Category category){
        categoryServices.save(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/edit/{id}")
    public String showEditCatePage(@PathVariable("id") Integer id, Model model){
        Category category = categoryServices.get(id);
        if(category == null){
            return "notfound";
        }else {

            model.addAttribute("category",category);
            return "admin/category/edit";
        }

    }
    @GetMapping("/delete/{id}")
    public String deleteCate(@PathVariable("id") Integer id){
        Category category = categoryServices.get(id);
        if(category == null){
            return "notfound";
        }else{
            categoryServices.delete(id);
            return "redirect:/admin/categories";
        }
    }
}
