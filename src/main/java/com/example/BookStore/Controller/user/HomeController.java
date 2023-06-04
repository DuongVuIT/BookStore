package com.example.BookStore.Controller.user;


import com.example.BookStore.entity.Product;
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
    @GetMapping
    public String home(Model model){
        List<Product> productList = productServices.getAllBooks();
        model.addAttribute("products", productList);
        return "home/index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "home/contact";
    }
    @GetMapping("/cart")
    public String cart(){
        return "home/cart";
    }
    @GetMapping("/cart/checkout")
    public String checkout(){
        return "home/cart/checkout";
    }
    @GetMapping("/product/detail")
    public String detail(){
        return "home/product/detail";
    }
}
