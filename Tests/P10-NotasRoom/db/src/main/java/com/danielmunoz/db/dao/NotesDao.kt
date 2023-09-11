package com.danielmunoz.db.dao

import androidx.room.*
import com.danielmunoz.db.entity.Note
import com.danielmunoz.db.model.NoteWithTags
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAll(): Single<List<Note>>

    @Query("SELECT * FROM notes WHERE noteId =:noteId")
    fun find(noteId: Int): Single<Note>

    @Transaction
    @Query("SELECT * FROM notes")
    fun getNotesWithTags(): Single<List<NoteWithTags>>

    @Transaction
    @Query("SELECT * FROM notes WHERE noteId =:noteId")
    fun findWithNotes(noteId: Int): Single<NoteWithTags>

    @Insert
    fun insertNote(note: Note): Completable

    @Update
    fun updateNote(note: Note): Completable

    @Delete
    fun deleteNote(note: Note): Completable
}