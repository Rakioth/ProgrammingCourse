package com.raks.room.domain.usecase.notes

import com.raks.room.domain.model.NoteWithTagsDto
import com.raks.room.domain.repository.NotesRepository

class UpdateNoteWithTags(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(noteWithTags: NoteWithTagsDto) =
        repository.updateNoteWithTags(noteWithTags)

}