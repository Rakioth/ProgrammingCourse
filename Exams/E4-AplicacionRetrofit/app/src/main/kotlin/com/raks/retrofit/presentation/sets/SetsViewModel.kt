package com.raks.retrofit.presentation.sets

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
class SetsViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<SetsState> = MutableStateFlow(SetsState())
            val state:  StateFlow<SetsState>        = _state

    fun onEvent(event: SetsEvent) {
        when (event) {

            is SetsEvent.LoadMore -> {
                viewModelScope.launch {
                    when (val result = pokemonUseCases.getSets(_state.value.page)) {

                        is Resource.Success -> {
                            _state.update { setsState ->
                                val updatedSets = setsState.sets.toMutableList()
                                result.data?.let { updatedSets.addAll(it.data) }

                                setsState.copy(
                                    page = (setsState.page + 1),
                                    sets = updatedSets,
                                )
                            }
                        }

                        is Resource.Error   -> {
                            result.message?.let {
                                Log.e(SetsViewModel::class.java.name, it)
                            }
                        }

                    }
                }
            }

        }
    }

}