package com.raks.retrofit.data.remote

import com.squareup.moshi.Json

data class CardDetailsDto(
    @Json(name = "data")
    val data: Card,
) {
    data class Card(
        @Json(name = "name")
        val name:       String,
        @Json(name = "number")
        val number:     String,
        @Json(name = "rarity")
        val rarity:     String,
        @Json(name = "flavorText")
        val flavorText: String,
        @Json(name = "images")
        val images:     Images,
        @Json(name = "cardmarket")
        val cardmarket: CardMarket,
    ) {
        data class Images(
            @Json(name = "large")
            val large: String,
        )
        data class CardMarket(
            @Json(name = "updatedAt")
            val updatedAt: String,
            @Json(name = "prices")
            val prices:    Prices,
        ) {
            data class Prices(
                @Json(name = "averageSellPrice")
                val averageSellPrice: Double,
                @Json(name = "lowPrice")
                val lowPrice:         Double,
                @Json(name = "trendPrice")
                val trendPrice:       Double,
            )
        }
    }
}