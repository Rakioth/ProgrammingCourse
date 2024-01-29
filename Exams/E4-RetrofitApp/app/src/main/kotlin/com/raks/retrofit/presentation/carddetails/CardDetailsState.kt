package com.raks.retrofit.presentation.carddetails

import com.raks.retrofit.domain.model.CardDetails

data class CardDetailsState(
    val isVisible:   Boolean      = false,
    val cardDetails: CardDetails? = null,
)