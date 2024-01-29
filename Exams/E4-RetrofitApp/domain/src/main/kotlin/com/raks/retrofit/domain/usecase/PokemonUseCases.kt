package com.raks.retrofit.domain.usecase

import com.raks.retrofit.domain.usecase.pokemon.GetCard
import com.raks.retrofit.domain.usecase.pokemon.GetCards
import com.raks.retrofit.domain.usecase.pokemon.GetSets

data class PokemonUseCases(
    val getSets:  GetSets,
    val getCards: GetCards,
    val getCard:  GetCard,
)