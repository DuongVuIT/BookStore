package com.example.BookStore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home(){
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
