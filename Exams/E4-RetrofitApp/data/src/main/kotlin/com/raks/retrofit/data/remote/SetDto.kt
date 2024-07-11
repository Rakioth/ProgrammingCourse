package com.raks.retrofit.data.remote

import com.squareup.moshi.Json

data class SetDto(
    @Json(name = "id")
    val id:     String,
    @Json(name = "name")
    val name:   String,
    @Json(name = "images")
    val images: Images,
) {
    data class Images(
        @Json(name = "symbol")
        val symbol: String,
        @Json(name = "logo")
        val logo:   String,
    )
}