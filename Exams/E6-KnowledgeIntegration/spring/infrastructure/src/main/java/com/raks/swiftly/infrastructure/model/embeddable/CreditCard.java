package com.raks.swiftly.infrastructure.model.embeddable;

import com.raks.swiftly.infrastructure.model.enums.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Embeddable
public class CreditCard {

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "type", nullable = false)
    private CardType  type;

    @Column(name = "number", nullable = false, unique = true)
    private String    number;

    @Column(name = "ccv", nullable = false)
    private String    ccv;

    @Column(name = "expired_date", nullable = false)
    private LocalDate expiredDate;

}