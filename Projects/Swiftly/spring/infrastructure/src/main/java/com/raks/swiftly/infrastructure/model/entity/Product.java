package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.domain.model.enums.ProductRating;
import com.raks.swiftly.infrastructure.model.embeddable.Audit;
import com.raks.swiftly.infrastructure.model.embeddable.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "sold_units")
    private Integer soldUnits;

    @Column(name = "accumulated_expenses", precision = 19, scale = 2)
    private BigDecimal accumulatedExpenses;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "request_threshold")
    private Integer requestThreshold;

    @Column(name = "hidden_threshold")
    private Integer hiddenThreshold;

    @Column(name = "on_sale")
    private Boolean onSale;

    @Column(name = "is_new")
    private Boolean isNew;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private ProductRating rating;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false, unique = true)
    private String model;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private List<Image> productImages = new ArrayList<>();

    @Column(name = "comments")
    private String comments;

    @Embedded
    private Audit audit;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Column(name = "cat", nullable = false)
    private String cat;

}