package com.raks.room.domain.usecase.notes

import com.raks.room.domain.model.NoteDto
import com.raks.room.domain.repository.NotesRepository

class DeleteNoteAndCrossReferences(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(note: NoteDto) =
        repository.deleteNoteAndCrossReferences(note)

}