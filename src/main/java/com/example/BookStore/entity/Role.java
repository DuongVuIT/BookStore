package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maquyen;
    @Column(name = "tenquyen")
    private String tenquyen;
    @ManyToMany (mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    public Role() {
    }
    public Role(String tenquyen)
    {
        super();
        this.tenquyen =  tenquyen;
    }
    public int getMaquyen() {
        return maquyen;
    }

    public void setMaquyen(int maquyen) {
        this.maquyen = maquyen;
    }

    public String getTenquyen() {
        return tenquyen;
    }

    public void setTenquyen(String tenquyen) {
        this.tenquyen = tenquyen;
    }
}
