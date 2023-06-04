package com.example.BookStore.services;

import com.example.BookStore.entity.Category;
import com.example.BookStore.entity.Product;
import com.example.BookStore.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAllBooks() {
        return productRepository.findAll();
    }

    public Product get(Integer id){
        return productRepository.findById(id).get();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void editProduct(Product product) {
        // Kiểm tra xem sản phẩm có tồn tại trong cơ sở dữ liệu hay không
        Product existingProduct = productRepository.findById(product.getMasp()).orElse(null);

        if (existingProduct != null) {
            // Cập nhật thông tin sản phẩm
            existingProduct.setTensp(product.getTensp());
            existingProduct.setHinhanh(product.getHinhanh()); // Lưu tên tệp ảnh mới vào cơ sở dữ liệu
            existingProduct.setGia(product.getGia());
            existingProduct.setMota(product.getMota());
            existingProduct.setSoluong(product.getSoluong());
            existingProduct.setNgaydang(product.getNgaydang());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setAuthor(product.getAuthor());

            productRepository.save(existingProduct);
        }
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
