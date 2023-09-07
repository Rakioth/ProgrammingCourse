package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.infrastructure.model.embeddable.ShoppingCartDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @ElementCollection
    @CollectionTable(name = "shopping_cart_details", joinColumns = @JoinColumn(name = "shopping_cart_id"))
    private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<>();

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    private Client client;

}