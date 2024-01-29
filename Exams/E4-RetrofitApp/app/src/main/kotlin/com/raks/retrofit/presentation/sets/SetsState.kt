package com.raks.retrofit.presentation.sets

import com.raks.retrofit.domain.model.Set

data class SetsState(
    val page: Int       = 1,
    val sets: List<Set> = emptyList(),
)