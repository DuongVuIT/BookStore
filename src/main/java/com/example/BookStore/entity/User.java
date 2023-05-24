package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int makh;
    @Column(name = "tendangnhap")
    private String username;
    @Column(name = "matkhau")
    private String password;
    @Column(name = "hoten")
    private String fullname;
    @Column(name = "sdt")
    private  int sdt;
    @Column(name = "email")
    private String email;
    @Column(name = "diachi")
    private String diachi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maquyen" ,referencedColumnName = "maquyen",foreignKey = @ForeignKey(name="FK_USER_ROLE"))
    private Role role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Order> orders;

}
