package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.domain.model.enums.OrderState;
import com.raks.swiftly.infrastructure.model.embeddable.OrderDetail;
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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long              id;

    @Column(name = "date", nullable = false, unique = true)
    private LocalDateTime     date;

    @ElementCollection
    @CollectionTable(name = "order_details", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Column(name = "total_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal        totalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client            client;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private OrderState        state;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "processed_by")
    private User              processedBy;

}