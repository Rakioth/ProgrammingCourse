package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.infrastructure.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class CatalogDetail {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

}