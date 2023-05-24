package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matacgia;
    @Column(name = "tentacgia")
    private String tentacgia;
    @Column(name = "email")
    private String email;
    @Column(name = "sdt")
    private int sdt;
    @Column(name = "diachi")
    private String diachi;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products;

}
