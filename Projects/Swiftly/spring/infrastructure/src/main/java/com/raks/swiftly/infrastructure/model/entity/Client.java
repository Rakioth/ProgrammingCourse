package com.raks.swiftly.infrastructure.model.entity;

import com.raks.swiftly.infrastructure.model.embeddable.Address;
import com.raks.swiftly.infrastructure.model.embeddable.Audit;
import com.raks.swiftly.infrastructure.model.embeddable.CreditCard;
import com.raks.swiftly.infrastructure.model.enums.ClientDocument;
import com.raks.swiftly.infrastructure.model.enums.ClientType;
import com.raks.swiftly.domain.model.enums.Gender;
import com.raks.swiftly.infrastructure.model.enums.CountryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "country_code", nullable = false)
    private CountryType countryCode;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "document_type", nullable = false)
    private ClientDocument documentType;

    @Column(name = "document", nullable = false, unique = true)
    private String document;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surnames", nullable = false)
    private String surnames;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "client_addresses", joinColumns = @JoinColumn(name = "client_id"))
    private List<Address> clientAddresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "client_credit_cards", joinColumns = @JoinColumn(name = "client_id"))
    private List<CreditCard> clientCreditCards = new ArrayList<>();

    @Column(name = "accumulated_expenses", precision = 19, scale = 2)
    private BigDecimal accumulatedExpenses;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "type", nullable = false)
    private ClientType type;

    @Column(name = "comments")
    private String comments;

    @Column(name = "license")
    private Boolean license;

    @Embedded
    private Audit audit;

}