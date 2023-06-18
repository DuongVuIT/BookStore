package com.example.BookStore.Controller.admin;

import com.example.BookStore.entity.Author;
import com.example.BookStore.entity.Category;
import com.example.BookStore.entity.Product;
import com.example.BookStore.services.AuthorService;
import com.example.BookStore.services.ProductServices;
import com.example.BookStore.services.CategoryServices;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private CategoryServices categoryServices;
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String showAllProduct(Model model, @RequestParam(defaultValue = "0") Integer pageNo,
                                 @RequestParam(defaultValue = "20") Integer pageSize){
        model.addAttribute("products", productServices.getAllProduct(pageNo,pageSize));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",productServices.getAllProduct(pageNo, pageSize).size() / pageSize );
        model.addAttribute("categories",categoryServices.getAllCate());

        return "admin/product/list";
    }
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryServices.getAllCate());
        model.addAttribute("authors", authorService.getAllAuthors()); // Lấy danh sách tác giả từ service
        return "admin/product/new_product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product/error";
        } else {
            Category selectedCategory = categoryServices.get(product.getCategory().getMaloai());
            Author selectedAuthor = authorService.get(product.getAuthor().getMatacgia());
            product.setCategory(selectedCategory);
            product.setAuthor(selectedAuthor);
            productServices.addProduct(product);
            return "redirect:/admin/product";
        }
    }
    @GetMapping("/edit/{id}")
    public String showEditProductPage(@PathVariable("id") Integer id, Model model) {
        Product product = productServices.get(id);
        if (product == null) {
            return "notfound";
        } else {
            List<Category> categories = categoryServices.getAllCate();
            List<Author> authors = authorService.getAllAuthors();
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryServices.getAllCate());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "admin/product/edit";
        }
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Integer id, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product/error";
        } else {
            Category selectedCategory = categoryServices.get(product.getCategory().getMaloai());
            Author selectedAuthor = authorService.get(product.getAuthor().getMatacgia());
            product.setCategory(selectedCategory);
            product.setAuthor(selectedAuthor);
            productServices.editProduct(product);
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        Product product = productServices.get(id);
        if(product == null){
            return "notfound";
        }else{
            productServices.deleteProduct(id);
            return "redirect:/admin/product";
        }
    }

}
