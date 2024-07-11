package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.infrastructure.model.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ShoppingCartDetail {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "units", nullable = false)
    private Integer units;

}