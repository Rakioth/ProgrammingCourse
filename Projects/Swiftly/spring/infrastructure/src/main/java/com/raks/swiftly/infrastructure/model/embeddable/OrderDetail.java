package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.infrastructure.model.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class OrderDetail {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "units", nullable = false)
    private Integer units;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

}