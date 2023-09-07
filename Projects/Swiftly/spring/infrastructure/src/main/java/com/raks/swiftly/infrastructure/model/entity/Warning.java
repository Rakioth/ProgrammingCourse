package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.domain.model.enums.WarningType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "warnings")
public class Warning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WarningType type;

    @Column(name = "processed_date")
    private LocalDate processedDate;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "processed_by")
    private User processedBy;

}