package com.raks.retrofit.domain.model

data class Set(
    val id:     String,
    val name:   String,
    val images: Images,
) {
    data class Images(
        val symbol: String,
        val logo:   String,
    )
}