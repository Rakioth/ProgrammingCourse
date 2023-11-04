package com.raks.room.domain.usecase.notes

import com.raks.room.domain.model.NoteWithTagsDto
import com.raks.room.domain.repository.NotesRepository

class FindNoteWithTags(
    private val repository: NotesRepository
) {

    suspend operator fun invoke(nid: Int): NoteWithTagsDto =
        repository.findNoteWithTags(nid)

}