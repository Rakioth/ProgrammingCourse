package com.raks.retrofit.data.remote

import com.squareup.moshi.Json

data class CardDto(
    @Json(name = "id")
    val id:     String,
    @Json(name = "images")
    val images: Images,
) {
    data class Images(
        @Json(name = "small")
        val small: String,
    )
}