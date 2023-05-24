package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int madonhang;
    @Column(name = "tenkhachhang")
    private String tenkhachhang;
    @Column(name = "sdt")
    private int sdt;
    @Column(name = "email")
    private String email;
    @Column(name = "diachi")
    private String diachi;
    @Column(name = "mota")
    private String mota;
    @Column(name = "ngaymua")
    private Date ngaymua;
    @Column(name = "tongtien")
    private double tongtien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "makh" ,referencedColumnName = "makh",foreignKey = @ForeignKey(name="FK_ORDER_USER"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masp" ,referencedColumnName = "masp",foreignKey = @ForeignKey(name="FK_ORDER_PRODUCT"))
    private Product product;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orderdetails",
            joinColumns = {@JoinColumn(name = "madonhang")},
            inverseJoinColumns = {@JoinColumn(name = "masp")})
    private Set<Product> products = new HashSet<>();
}

