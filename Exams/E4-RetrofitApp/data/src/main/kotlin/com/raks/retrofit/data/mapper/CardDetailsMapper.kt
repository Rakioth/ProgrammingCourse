package com.raks.retrofit.data.mapper

import com.raks.retrofit.data.remote.CardDetailsDto
import com.raks.retrofit.domain.model.CardDetails

fun CardDetailsDto.toDomain() = CardDetails(
    CardDetails.Card(
        name       = data.name,
        number     = data.number,
        rarity     = data.rarity,
        flavorText = data.flavorText,
        images     = CardDetails.Card.Images(
            large = data.images.large,
        ),
        cardmarket = CardDetails.Card.CardMarket(
            updatedAt = data.cardmarket.updatedAt,
            prices    = CardDetails.Card.CardMarket.Prices(
                averageSellPrice = data.cardmarket.prices.averageSellPrice,
                lowPrice         = data.cardmarket.prices.lowPrice,
                trendPrice       = data.cardmarket.prices.trendPrice,
            ),
        ),
    )
)