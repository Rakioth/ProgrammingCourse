package com.raks.room.data.dao

import androidx.room.*
import com.raks.room.data.embedded.*
import com.raks.room.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotwayDao {

    @Query("SELECT * FROM notes")
            fun getAllNotes():               Flow<List<Note>>

    @Transaction
    @Query("SELECT * FROM notes WHERE nid = :nid")
    suspend fun findNoteWithTags(nid: Int):  NoteWithTags

    @Insert
    suspend fun insertNote(note: Note):      Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Transaction
    @Query("SELECT * FROM tags")
            fun getTagsWithNotes():          Flow<List<TagWithNotes>>

    @Query("SELECT * FROM tags")
    suspend fun getAllTags():                List<Tag>

    @Insert
    suspend fun insertTags(tags: List<Tag>): List<Long>

    @Update
    suspend fun updateTags(tags: List<Tag>)

    @Insert
    suspend fun insertTag(tag: Tag):         Long

    @Update
    suspend fun updateTag(tag: Tag)

    @Delete
    suspend fun deleteTag(tag: Tag)

    @Transaction
    suspend fun insertNoteWithTags(noteWithTags: NoteWithTags) {
        val noteId       = insertNote(noteWithTags.note)
        val newTagIds    = insertTags(noteWithTags.tags.filter { it.tid == 0 })
        val existingTags = noteWithTags.tags.filter { it.tid != 0 }
        insertCrossRefs(newTagIds, existingTags, noteId.toInt())
    }

    @Transaction
    suspend fun updateNoteWithTags(noteWithTags: NoteWithTags) {
        updateNote(noteWithTags.note)
        val newTagIds    = insertTags(noteWithTags.tags.filter { it.tid == 0 })
        val existingTags = noteWithTags.tags.filter { it.tid != 0 }
        updateTags(existingTags)
        deleteCrossRefsByNoteId(noteWithTags.note.nid)
        insertCrossRefs(newTagIds, existingTags, noteWithTags.note.nid)
    }

    @Insert
    suspend fun insertCrossRefs(noteTagCrossRefs: List<NoteTagCrossRef>)

    private suspend fun insertCrossRefs(newTagIds: List<Long>, existingTags: List<Tag>, nid: Int) {
        val tagIdsToCrossReference = newTagIds.map { it.toInt() } + existingTags.map { it.tid }
        val notesTagCrossRefs      = tagIdsToCrossReference.map { NoteTagCrossRef(nid, it) }
        insertCrossRefs(notesTagCrossRefs)
    }

    @Query("DELETE FROM note_tag_ref WHERE nid = :nid")
    suspend fun deleteCrossRefsByNoteId(nid: Int)

    @Query("DELETE FROM note_tag_ref WHERE tid = :tid")
    suspend fun deleteCrossRefsByTagId(tid: Int)

    suspend fun deleteNoteAndCrossReferences(note: Note) {
        deleteCrossRefsByNoteId(note.nid)
        deleteNote(note)
    }

    suspend fun deleteTagAndCrossReferences(tag: Tag) {
        deleteCrossRefsByTagId(tag.tid)
        deleteTag(tag)
    }

}