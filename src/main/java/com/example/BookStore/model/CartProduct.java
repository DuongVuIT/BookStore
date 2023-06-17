package com.example.BookStore.model;

import com.example.BookStore.entity.Product;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    private Product product;
    private int quantity;
}
