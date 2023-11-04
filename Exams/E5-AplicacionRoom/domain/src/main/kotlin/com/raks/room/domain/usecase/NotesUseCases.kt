package com.raks.room.domain.usecase

import com.raks.room.domain.usecase.notes.*

data class NotesUseCases(
    val getAllNotes:                  GetAllNotes,
    val findNoteWithTags:             FindNoteWithTags,
    val insertNoteWithTags:           InsertNoteWithTags,
    val updateNoteWithTags:           UpdateNoteWithTags,
    val deleteNoteAndCrossReferences: DeleteNoteAndCrossReferences,
)