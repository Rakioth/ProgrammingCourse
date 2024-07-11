package com.raks.jpa.dto

import java.io.Serializable
import java.time.LocalDate

data class AlbumDto(
    var title:       String?                = null,
    var genre:       GenreDto?              = null,
    var releaseDate: LocalDate?             = null,
    var sales:       Float?                 = null,
    var hasVinyl:    Boolean?               = false,
    var songs:       MutableList<SongDto>   = mutableListOf(),
    var artists:     MutableList<ArtistDto> = mutableListOf(),
) : Serializable, JpaDto<Int>()