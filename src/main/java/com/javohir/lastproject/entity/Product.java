package com.javohir.lastproject.entity;

import com.javohir.lastproject.entity.photo.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @OneToOne
    private Attachment photo;

    @ManyToOne
    private Category category;

    public Product(String name, String description, double price, Attachment photo, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.category = category;
    }

    public Product(String name, String description, double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
