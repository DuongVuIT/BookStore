package com.example.BookStore.Controller.user;

import com.example.BookStore.entity.Category;
import com.example.BookStore.entity.Product;
import com.example.BookStore.services.CategoryServices;
import com.example.BookStore.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ProductServices productService;
    @Autowired
    private CategoryServices categoryServices;
    public SearchController(ProductServices productService) {
        this.productService = productService;
    }
    @PostMapping
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchByName(keyword);
        List<Category> categories = categoryServices.getAllCate();
        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);
        model.addAttribute("products", products);
        return "user/product/search_result";
    }

}
