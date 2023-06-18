package com.example.BookStore.Controller.user;


import com.example.BookStore.entity.Category;
import com.example.BookStore.entity.Product;
import com.example.BookStore.services.CategoryServices;
import com.example.BookStore.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private CategoryServices categoryServices;
    @GetMapping("/")
    public String home(Model model, @RequestParam(defaultValue = "0") Integer pageNo,
                       @RequestParam(defaultValue = "6") Integer pageSize ){
        model.addAttribute("products", productServices.getAllProduct(pageNo,pageSize));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",productServices.getAllProduct(pageNo, pageSize).size() / pageSize );
        model.addAttribute("categories",categoryServices.getAllCate());
        return "user/home/index";
    }
    @GetMapping("/contact")
    public String contact(){
        return "user/home/contact";
    }

    @GetMapping("/product/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        // Lấy thông tin sản phẩm từ cơ sở dữ liệu dựa trên productId
        Product product = productServices.get(id);

        // Đặt dữ liệu sản phẩm vào model
        model.addAttribute("product", product);

        return "user/product/detail";
    }

}
