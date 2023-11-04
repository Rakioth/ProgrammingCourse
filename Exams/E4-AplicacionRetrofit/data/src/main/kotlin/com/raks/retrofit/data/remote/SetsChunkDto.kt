package com.raks.retrofit.data.remote

import com.squareup.moshi.Json

data class SetsChunkDto(
    @Json(name = "data")
    val data: List<SetDto>,
)