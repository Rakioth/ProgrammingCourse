package com.raks.retrofit.domain.model

data class Card(
    val id:     String,
    val images: Images,
) {
    data class Images(
        val small: String,
    )
}