package com.raks.retrofit.presentation.cards

import com.raks.retrofit.domain.model.Card

data class CardsState(
    val page: Int         = 1,
    val cards: List<Card> = emptyList(),
)