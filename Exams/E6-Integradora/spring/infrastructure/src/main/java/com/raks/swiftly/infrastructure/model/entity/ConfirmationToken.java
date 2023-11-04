package com.raks.swiftly.infrastructure.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "confirmation_tokens")
public class ConfirmationToken {

    @Id
    @Column(name = "email", nullable = false)
    private String        email;

    @Column(name = "token", nullable = false)
    private String        token;

    @Column(name = "expired_date", nullable = false)
    private LocalDateTime expiredDate;

    @Column(name = "verified", nullable = false)
    private Boolean       verified    = false;

}