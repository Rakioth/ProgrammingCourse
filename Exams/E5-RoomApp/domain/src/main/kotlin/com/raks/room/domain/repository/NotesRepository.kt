package com.raks.room.domain.repository

import com.raks.room.domain.model.NoteDto
import com.raks.room.domain.model.NoteWithTagsDto
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

            fun getAllNotes():                                     Flow<List<NoteDto>>

    suspend fun findNoteWithTags(nid: Int):                        NoteWithTagsDto

    suspend fun insertNoteWithTags(noteWithTags: NoteWithTagsDto)

    suspend fun updateNoteWithTags(noteWithTags: NoteWithTagsDto)

    suspend fun deleteNoteAndCrossReferences(note: NoteDto)

}