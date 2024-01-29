package com.raks.retrofit.data.mapper

import com.raks.retrofit.data.remote.CardDto
import com.raks.retrofit.domain.model.Card

fun CardDto.toDomain() = Card(
    id     = id,
    images = Card.Images(
        small = images.small,
    ),
)