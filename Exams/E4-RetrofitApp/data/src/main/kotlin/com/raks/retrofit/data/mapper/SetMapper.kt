package com.raks.retrofit.data.mapper

import com.raks.retrofit.data.remote.SetDto
import com.raks.retrofit.domain.model.Set

fun SetDto.toDomain() = Set(
    id     = id,
    name   = name,
    images = Set.Images(
        symbol = images.symbol,
        logo   = images.logo,
    ),
)