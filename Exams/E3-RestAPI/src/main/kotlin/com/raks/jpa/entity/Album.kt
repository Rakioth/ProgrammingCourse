package com.raks.jpa.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "albums")
open class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id:          Int?                = null

    @Column(name = "title", nullable = false)
    open var title:       String?             = null

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    open var genre:       Genre?              = null

    @Column(name = "release_date", nullable = false)
    open var releaseDate: LocalDate?          = null

    @Column(name = "sales", nullable = false)
    open var sales:       Float?              = null

    @Column(name = "has_vinyl", nullable = false)
    open var hasVinyl:    Boolean?            = false

    @OneToMany(mappedBy = "album", orphanRemoval = true)
    open var songs:       MutableList<Song>   = mutableListOf()

    @ManyToMany
    @JoinTable(
        name               = "album_artists",
        joinColumns        = [JoinColumn(name = "album_id")],
        inverseJoinColumns = [JoinColumn(name = "artist_id")],
    )
    open var artists:     MutableList<Artist> = mutableListOf()

}