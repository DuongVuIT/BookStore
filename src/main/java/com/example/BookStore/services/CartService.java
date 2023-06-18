package com.example.BookStore.services;

import com.example.BookStore.entity.Product;
import com.example.BookStore.model.CartProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {
    private List<CartProduct> cartItems = new ArrayList<>();
    public void addProduct(Product product) {
        for (CartProduct item : cartItems) {
            if (item.getProduct().getMasp() == (product.getMasp())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }

        // Nếu chưa tồn tại, tạo một mục hàng mới và thêm vào giỏ hàng
        CartProduct newItem = new CartProduct();
        newItem.setProduct(product);
        newItem.setQuantity(1);
        cartItems.add(newItem);
    }

    public void addProductDetail(Product product) {
        boolean isExisting = false;

        for (CartProduct item : cartItems) {
            if (item.getProduct().getMasp() == product.getMasp()) {
                int newQuantity = item.getQuantity() + product.getSoluong();
                item.setQuantity(newQuantity);
                isExisting = true;
                break;
            }
        }

        if (!isExisting) {
            CartProduct newItem = new CartProduct();
            newItem.setProduct(product);
            newItem.setQuantity(product.getSoluong());
            cartItems.add(newItem);
        }
    }

    public List<CartProduct> getCartItems() {
        return cartItems;
    }
    public void updateQuantity(Integer id, int quantity) {
        for (CartProduct item : cartItems) {
            if (item.getProduct().getMasp() == id) {
                item.setQuantity(quantity);
            }
        }

    }
    public void removeProduct(Integer productId) {
        cartItems.removeIf(item -> item.getProduct().getMasp() == productId);
    }

}
