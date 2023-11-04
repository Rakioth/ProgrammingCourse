package com.raks.retrofit.domain.usecase

import com.raks.retrofit.domain.usecase.pokemon.*

data class PokemonUseCases(
    val getSets:  GetSets,
    val getCards: GetCards,
    val getCard:  GetCard,
)