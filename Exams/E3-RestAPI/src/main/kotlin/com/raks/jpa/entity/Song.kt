package com.raks.jpa.entity

import jakarta.persistence.*
import java.time.LocalTime

@Entity
@Table(name = "songs")
open class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id:    Int?       = null

    @Column(name = "name", nullable = false)
    open var name:  String?    = null

    @Column(name = "time", nullable = false)
    open var time:  LocalTime? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    open var album: Album?     = null

}