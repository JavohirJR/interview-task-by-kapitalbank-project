package com.javohir.lastproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 14)
    private String name;

    @Column(nullable = false, length = 3)
    private String country;

    @Column(nullable = false, length = 50, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String text;

    public Customer(String name, String country, String phoneNumber, String text) {
        this.name = name;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.text = text;
    }
}
