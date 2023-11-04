package com.raks.retrofit.presentation.carddetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.retrofit.domain.usecase.PokemonUseCases
import com.raks.retrofit.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<CardDetailsState> = MutableStateFlow(CardDetailsState())
            val state:  StateFlow<CardDetailsState>        = _state

    fun onEvent(event: CardDetailsEvent) {
        when (event) {

            is CardDetailsEvent.ToggleInfo -> {
                _state.update {
                    it.copy(
                        isVisible = !_state.value.isVisible
                    )
                }
            }

            is CardDetailsEvent.LoadInfo -> {
                viewModelScope.launch {
                    when (val result = pokemonUseCases.getCard(event.id)) {

                        is Resource.Success -> {
                            result.data?.let {
                                _state.update { cardDetailsState ->
                                    cardDetailsState.copy(
                                        cardDetails = it
                                    )
                                }
                            }
                        }

                        is Resource.Error   -> {
                            result.message?.let {
                                Log.e(CardDetailsViewModel::class.java.name, it)
                            }
                        }

                    }
                }
            }

        }
    }

}