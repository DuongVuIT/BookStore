package com.example.BookStore.Controller.user;

import com.example.BookStore.entity.Product;

import com.example.BookStore.model.CartProduct;
import com.example.BookStore.services.CartService;
import com.example.BookStore.services.ProductServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private ProductServices productServices;
    @Autowired
    private CartService cartService;
    @PostMapping("/cart/{id}")
    public String addToCart(@PathVariable Integer id) {
        Product product = productServices.get(id);
        if (product != null) {
            cartService.addProduct(product);
        }
        return "redirect:/";
    }
    @PostMapping("/detail/cart/{id}")
    public String addDetailToCart(@PathVariable Integer id, @RequestParam("quantity") Integer quantity) {
        Product product = productServices.get(id);
        if (product != null) {
            product.setSoluong(quantity); // Cập nhật số lượng sản phẩm
            cartService.addProductDetail(product);
        }
        return "redirect:/";
    }
    @GetMapping("/cart")
    public String showCart(Model model) {
        List<CartProduct> cart = cartService.getCartItems();
        if (cart == null) {
            model.addAttribute("check", false);
            model.addAttribute("products", new ArrayList<Product>());
            return "cart/index";
        }
        model.addAttribute("check", true);
        model.addAttribute("products", cart);
        return "user/cart/index";
    }
    @PutMapping("/cart/update/{id}")
    public ResponseEntity<String> updateQuantity(@PathVariable int id, @RequestParam int quantity) {
        cartService.updateQuantity(id, quantity);
        return new ResponseEntity<>("Quantity updated successfully", HttpStatus.OK);
    }
    @RequestMapping(value = "/cart/remove/{productId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String removeProduct(@PathVariable Integer productId, RedirectAttributes redirectAttributes) {
        cartService.removeProduct(productId);
        redirectAttributes.addFlashAttribute("message", "Product removed successfully");
        return "redirect:/cart";
    }

}