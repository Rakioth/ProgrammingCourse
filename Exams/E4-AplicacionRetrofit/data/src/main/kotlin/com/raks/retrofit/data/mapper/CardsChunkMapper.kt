package com.raks.retrofit.data.mapper

import com.raks.retrofit.data.remote.CardsChunkDto
import com.raks.retrofit.domain.model.CardsChunk

fun CardsChunkDto.toDomain() = CardsChunk(
    data = data.map { it.toDomain() }
)