package com.raks.room.presentation.editnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raks.room.domain.model.NoteWithTagsDto
import com.raks.room.domain.usecase.NotesUseCases
import com.raks.room.domain.usecase.TagsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases,
    private val tagsUseCases:  TagsUseCases,
) : ViewModel() {

    private val _state: MutableStateFlow<EditNoteState> = MutableStateFlow(EditNoteState())
            val state:  StateFlow<EditNoteState>        = _state

    fun onEvent(event: EditNoteEvent) {
        when (event) {

            is EditNoteEvent.LoadInfo         -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            noteWithTags = if (event.nid > 0) notesUseCases.findNoteWithTags(event.nid) else NoteWithTagsDto.empty(),
                            tags         = tagsUseCases.getAllTags(),
                        )
                    }
                }
            }

            is EditNoteEvent.SaveNoteWithTags -> {
                viewModelScope.launch {
                    if (event.noteWithTags.note.nid > 0)
                        notesUseCases.updateNoteWithTags(event.noteWithTags)
                    else
                        notesUseCases.insertNoteWithTags(event.noteWithTags)
                }
            }

        }
    }

}