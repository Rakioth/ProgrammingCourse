package com.raks.room.data.repository

import com.raks.room.data.dao.NotwayDao
import com.raks.room.data.mapper.toDomain
import com.raks.room.data.mapper.toEntity
import com.raks.room.domain.model.NoteDto
import com.raks.room.domain.model.NoteWithTagsDto
import com.raks.room.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val dao: NotwayDao
) : NotesRepository {

    override         fun getAllNotes():                                     Flow<List<NoteDto>> =
        dao.getAllNotes().map { notes -> notes.map { it.toDomain() } }

    override suspend fun findNoteWithTags(nid: Int):                        NoteWithTagsDto     =
        dao.findNoteWithTags(nid).toDomain()

    override suspend fun insertNoteWithTags(noteWithTags: NoteWithTagsDto)                      =
        dao.insertNoteWithTags(noteWithTags.toEntity())

    override suspend fun updateNoteWithTags(noteWithTags: NoteWithTagsDto)                      =
        dao.updateNoteWithTags(noteWithTags.toEntity())

    override suspend fun deleteNoteAndCrossReferences(note: NoteDto)                            =
        dao.deleteNoteAndCrossReferences(note.toEntity())

}