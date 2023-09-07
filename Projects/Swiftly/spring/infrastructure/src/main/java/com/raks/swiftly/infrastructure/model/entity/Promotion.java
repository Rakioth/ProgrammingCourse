package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.infrastructure.model.embeddable.Audit;
import com.raks.swiftly.infrastructure.model.embeddable.Period;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Embedded
    private Period period;

    @OneToMany(mappedBy = "promotion")
    private List<Product> products = new ArrayList<>();

    @Column(name = "discount", nullable = false, unique = true, precision = 19, scale = 2)
    private BigDecimal discount;

    @Embedded
    private Audit audit;

}