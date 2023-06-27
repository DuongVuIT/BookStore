package com.example.BookStore.Controller.user;


import com.example.BookStore.entity.Category;
import com.example.BookStore.entity.Product;
import com.example.BookStore.services.CategoryServices;
import com.example.BookStore.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private CategoryServices categoryServices;
    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productServices.getAllBooks();
        List<Category> categories = categoryServices.getAllCate();
        model.addAttribute("products", productList);
        model.addAttribute("categories", categories);
        return "user/home/index";
    }
    @GetMapping("/contact")
    public String contact(){
        return "user/home/contact";
    }
    @GetMapping("/cart")
    public String cart(){
        return "user/cart/cart";
    }
    @GetMapping("/cart/checkout")
    public String checkout(){
        return "user/cart/checkout";
    }
    @GetMapping("/product/detail")
    public String detail(){
        return "user/product/detail";
    }


}
