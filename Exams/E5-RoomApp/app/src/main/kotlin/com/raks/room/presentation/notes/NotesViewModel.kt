package com.raks.room.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.room.domain.usecase.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    val state = notesUseCases
        .getAllNotes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onEvent(event: NotesEvent) {
        when (event) {

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNoteAndCrossReferences(event.note)
                }
            }

        }
    }

}