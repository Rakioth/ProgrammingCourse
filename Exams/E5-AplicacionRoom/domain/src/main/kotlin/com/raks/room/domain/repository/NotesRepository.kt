package com.raks.room.domain.repository

import com.raks.room.domain.model.*
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

            fun getAllNotes():                                     Flow<List<NoteDto>>

    suspend fun findNoteWithTags(nid: Int):                        NoteWithTagsDto

    suspend fun insertNoteWithTags(noteWithTags: NoteWithTagsDto)

    suspend fun updateNoteWithTags(noteWithTags: NoteWithTagsDto)

    suspend fun deleteNoteAndCrossReferences(note: NoteDto)

}