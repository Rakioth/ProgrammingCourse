package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.infrastructure.model.embeddable.Address;
import com.raks.swiftly.infrastructure.model.embeddable.Audit;
import com.raks.swiftly.infrastructure.model.enums.SupplierDocument;
import com.raks.swiftly.infrastructure.model.enums.SupplierType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long             id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "document_type")
    private SupplierDocument documentType;

    @Column(name = "document", nullable = false, unique = true)
    private String           document;

    @Column(name = "landline", nullable = false)
    private String           landline;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String           phoneNumber;

    @Column(name = "name", nullable = false, unique = true)
    private String           name;

    @Embedded
    private Address          address;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "type", nullable = false)
    private SupplierType     type;

    @Column(name = "comments")
    private String           comments;

    @Embedded
    private Audit            audit;

}