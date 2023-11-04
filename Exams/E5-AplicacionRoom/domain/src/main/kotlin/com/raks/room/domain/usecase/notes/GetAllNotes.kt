package com.raks.room.domain.usecase.notes

import com.raks.room.domain.model.NoteDto
import com.raks.room.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotes(
    private val repository: NotesRepository
) {

    operator fun invoke(): Flow<List<NoteDto>> =
        repository.getAllNotes()

}