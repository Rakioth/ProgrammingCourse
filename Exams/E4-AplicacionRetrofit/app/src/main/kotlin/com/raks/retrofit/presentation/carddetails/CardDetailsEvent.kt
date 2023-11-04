package com.raks.retrofit.presentation.carddetails

sealed class CardDetailsEvent {
    object     ToggleInfo               : CardDetailsEvent()
    data class LoadInfo(val id: String) : CardDetailsEvent()
}