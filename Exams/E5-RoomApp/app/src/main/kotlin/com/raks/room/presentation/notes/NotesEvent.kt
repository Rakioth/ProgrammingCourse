package com.raks.room.presentation.notes

import com.raks.room.domain.model.NoteDto

sealed class NotesEvent {
    data class DeleteNote(val note: NoteDto) : NotesEvent()
}