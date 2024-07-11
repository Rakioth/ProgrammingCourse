package com.raks.retrofit.data

import com.raks.retrofit.data.remote.CardDetailsDto
import com.raks.retrofit.data.remote.CardsChunkDto
import com.raks.retrofit.data.remote.SetsChunkDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    companion object {
        private const val SETS_SIZE  = 6
        private const val CARDS_SIZE = 12
        private const val ORDER_BY   = "-releaseDate"

        const val BASE_URL           = "https://api.pokemontcg.io/v2/"
        const val SET_ID             = "set.id:%s"
    }

    @GET("sets")
    suspend fun getSetsData(
        @Query("page")     page:     Int,
        @Query("pageSize") setsSize: Int    = SETS_SIZE,
        @Query("orderBy")  orderBy:  String = ORDER_BY,
    ): SetsChunkDto

    @GET("cards")
    suspend fun getCardsData(
        @Query("page")     page:      Int,
        @Query("q")        sid:       String,
        @Query("pageSize") cardsSize: Int    = CARDS_SIZE,
    ): CardsChunkDto

    @GET("cards/{id}")
    suspend fun getCardDetailsData(
        @Path("id") id: String,
    ): CardDetailsDto

}