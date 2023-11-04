package com.raks.retrofit.data.repository

import com.raks.retrofit.data.PokemonApi
import com.raks.retrofit.data.mapper.toDomain
import com.raks.retrofit.domain.model.CardDetails
import com.raks.retrofit.domain.model.CardsChunk
import com.raks.retrofit.domain.model.SetsChunk
import com.raks.retrofit.domain.repository.PokemonRepository
import com.raks.retrofit.domain.util.Resource

class PokemonRepositoryImpl(
    private val api: PokemonApi
) : PokemonRepository {

    override suspend fun getSets(page: Int):               Resource<SetsChunk>   =
        try {
            Resource.Success(api.getSetsData(page).toDomain())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }

    override suspend fun getCards(page: Int, sid: String): Resource<CardsChunk>  =
        try {
            Resource.Success(api.getCardsData(page, sid).toDomain())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }

    override suspend fun getCard(id: String):              Resource<CardDetails> =
        try {
            Resource.Success(api.getCardDetailsData(id).toDomain())
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred")
        }

}