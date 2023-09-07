package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.infrastructure.model.embeddable.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_parent")
    private Category categoryParent;

    @OneToMany(orphanRemoval = true)
    @JoinTable(name = "categories_child",
               joinColumns = @JoinColumn(name = "category_1_id"),
               inverseJoinColumns = @JoinColumn(name = "categories_2_id"))
    private List<Category> categoriesChild = new ArrayList<>();

    @Embedded
    private Audit audit;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

}