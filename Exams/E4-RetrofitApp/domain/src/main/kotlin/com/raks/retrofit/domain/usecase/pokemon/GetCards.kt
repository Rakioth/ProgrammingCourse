package com.raks.retrofit.domain.usecase.pokemon

import com.raks.retrofit.domain.model.CardsChunk
import com.raks.retrofit.domain.repository.PokemonRepository
import com.raks.retrofit.domain.util.Resource

class GetCards(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(page: Int, sid: String): Resource<CardsChunk> =
        repository.getCards(page, sid)

}