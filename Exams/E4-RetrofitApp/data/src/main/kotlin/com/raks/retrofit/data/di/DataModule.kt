package com.raks.retrofit.data.di

import com.raks.retrofit.data.BuildConfig
import com.raks.retrofit.data.PokemonApi
import com.raks.retrofit.data.repository.*
import com.raks.retrofit.data.util.AuthInterceptor
import com.raks.retrofit.domain.repository.*
import com.raks.retrofit.domain.usecase.*
import com.raks.retrofit.domain.usecase.pokemon.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideClient():                                       OkHttpClient      =
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
            .build()

    @Provides
    @Singleton
    fun providePokemonApi(client: OkHttpClient):               PokemonApi        =
        Retrofit.Builder()
            .baseUrl(PokemonApi.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi):             PokemonRepository =
        PokemonRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePokemonUseCases(repository: PokemonRepository): PokemonUseCases   =
        PokemonUseCases(
            GetSets(repository),
            GetCards(repository),
            GetCard(repository),
        )

}