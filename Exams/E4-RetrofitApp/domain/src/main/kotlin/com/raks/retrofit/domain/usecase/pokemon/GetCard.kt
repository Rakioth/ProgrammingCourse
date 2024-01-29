package com.raks.retrofit.domain.usecase.pokemon

import com.raks.retrofit.domain.model.CardDetails
import com.raks.retrofit.domain.repository.PokemonRepository
import com.raks.retrofit.domain.util.Resource

class GetCard(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(id: String): Resource<CardDetails> =
        repository.getCard(id)

}