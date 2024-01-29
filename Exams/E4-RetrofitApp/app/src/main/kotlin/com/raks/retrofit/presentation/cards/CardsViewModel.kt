package com.raks.retrofit.presentation.cards

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.retrofit.data.PokemonApi
import com.raks.retrofit.domain.usecase.PokemonUseCases
import com.raks.retrofit.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<CardsState> = MutableStateFlow(CardsState())
            val state:  StateFlow<CardsState>        = _state

    fun onEvent(event: CardsEvent) {
        when (event) {

            is CardsEvent.LoadMore -> {
                viewModelScope.launch {
                    when (val result = pokemonUseCases.getCards(_state.value.page, PokemonApi.SET_ID.format(event.sid))) {

                        is Resource.Success -> {
                            _state.update { cardsState ->
                                val updatedCards = cardsState.cards.toMutableList()
                                result.data?.let { updatedCards.addAll(it.data) }

                                cardsState.copy(
                                    page  = (cardsState.page + 1),
                                    cards = updatedCards,
                                )
                            }
                        }

                        is Resource.Error   -> {
                            result.message?.let {
                                Log.e(CardsViewModel::class.java.name, it)
                            }
                        }

                    }
                }
            }

        }
    }

}