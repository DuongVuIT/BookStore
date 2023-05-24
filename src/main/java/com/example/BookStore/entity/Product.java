package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int masp;
    @Column(name = "tensp")
    private String tensp;
    @Column(name = "hinhanh")
    private String hinhanh;
    @Column(name = "gia")
    private double gia;
    @Column(name = "mota")
    private String mota;
    @Column(name = "soluong")
    private int soluong;
    @Column(name = "ngaydang")
    private Date ngaydang;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maloai" ,referencedColumnName = "maloai",foreignKey = @ForeignKey(name="FK_PRODUCT_CATEGORY"))
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matacgia" ,referencedColumnName = "matacgia",foreignKey = @ForeignKey(name="FK_PRODUCT_AUTHOR"))
    private Author author;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderdetails",
            joinColumns = {@JoinColumn(name = "masp")},
            inverseJoinColumns = {@JoinColumn(name = "madonhang")})
    private Set<Order> order = new HashSet<>();
}
