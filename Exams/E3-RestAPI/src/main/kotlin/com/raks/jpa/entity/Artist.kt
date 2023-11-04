package com.raks.jpa.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "artists")
open class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id:               Int?               = null

    @Column(name = "name", nullable = false)
    open var name:             String?            = null

    @Column(name = "birth_date", nullable = false)
    open var birthDate:        LocalDate?         = null

    @Column(name = "city", nullable = false)
    open var city:             String?            = null

    @Column(name = "monthly_listeners")
    open var monthlyListeners: Int?               = null

    @Column(name = "label", nullable = false)
    open var label:            String?            = null

    @ManyToMany(mappedBy = "artists")
    open var albums:           MutableList<Album> = mutableListOf()

}