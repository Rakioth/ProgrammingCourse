package com.raks.jpa.dto

import java.io.Serializable
import java.time.LocalTime

data class SongDto(
    var name:  String?    = null,
    var time:  LocalTime? = null,
) : Serializable, JpaDto<Int>()