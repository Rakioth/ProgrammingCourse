package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.infrastructure.model.embeddable.CatalogDetail;
import com.raks.swiftly.infrastructure.model.embeddable.Period;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "catalogs")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long                id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier            supplier;

    @Embedded
    private Period              period;

    @ElementCollection
    @CollectionTable(name = "catalog_details", joinColumns = @JoinColumn(name = "catalog_id"))
    private List<CatalogDetail> catalogDetails = new ArrayList<>();

}