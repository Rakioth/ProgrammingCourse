package com.raks.retrofit.presentation.cards

sealed class CardsEvent {
    data class LoadMore(val sid: String) : CardsEvent()
}