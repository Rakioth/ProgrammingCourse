package com.raks.retrofit.data.remote

import com.squareup.moshi.Json

data class CardsChunkDto(
    @Json(name = "data")
    val data: List<CardDto>,
)