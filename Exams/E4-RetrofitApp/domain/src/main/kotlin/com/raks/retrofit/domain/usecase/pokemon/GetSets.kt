package com.raks.retrofit.domain.usecase.pokemon

import com.raks.retrofit.domain.model.SetsChunk
import com.raks.retrofit.domain.repository.PokemonRepository
import com.raks.retrofit.domain.util.Resource

class GetSets(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(page: Int): Resource<SetsChunk> =
        repository.getSets(page)

}