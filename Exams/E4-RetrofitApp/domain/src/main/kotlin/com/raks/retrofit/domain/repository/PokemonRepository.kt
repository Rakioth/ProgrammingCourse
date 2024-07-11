package com.raks.retrofit.domain.repository

import com.raks.retrofit.domain.model.CardDetails
import com.raks.retrofit.domain.model.CardsChunk
import com.raks.retrofit.domain.model.SetsChunk
import com.raks.retrofit.domain.util.Resource

interface PokemonRepository {

    suspend fun getSets(page: Int):               Resource<SetsChunk>

    suspend fun getCards(page: Int, sid: String): Resource<CardsChunk>

    suspend fun getCard(id: String):              Resource<CardDetails>

}