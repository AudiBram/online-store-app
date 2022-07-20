package com.project.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
