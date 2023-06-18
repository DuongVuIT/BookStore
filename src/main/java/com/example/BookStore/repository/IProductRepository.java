package com.example.BookStore.repository;

import com.example.BookStore.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.tensp) LIKE LOWER(concat('%', :keyword, '%'))")
    List<Product> searchByName(@Param("keyword") String keyword);
    default List<Product> findAllProducts(Integer pageNo,
                                          Integer pageSize) {
        return findAll(PageRequest.of(pageNo,
                pageSize))
                .getContent();
    }
}
