package com.raks.retrofit.data.mapper

import com.raks.retrofit.data.remote.SetsChunkDto
import com.raks.retrofit.domain.model.SetsChunk

fun SetsChunkDto.toDomain() = SetsChunk(
    data = data.map { it.toDomain() }
)