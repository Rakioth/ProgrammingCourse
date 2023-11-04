package com.raks.jpa.dto

import java.io.Serializable
import java.time.LocalDate

data class ArtistDto(
    var name:             String?    = null,
    var birthDate:        LocalDate? = null,
    var city:             String?    = null,
    var monthlyListeners: Int?       = null,
    var label:            String?    = null,
) : Serializable, JpaDto<Int>()