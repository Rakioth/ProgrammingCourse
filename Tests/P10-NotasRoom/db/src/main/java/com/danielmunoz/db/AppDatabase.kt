package com.danielmunoz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danielmunoz.db.dao.NotesDao
import com.danielmunoz.db.dao.TagsDao
import com.danielmunoz.db.entity.Note
import com.danielmunoz.db.entity.NoteTagCrossRef
import com.danielmunoz.db.entity.Tag

@Database(entities = [Note::class, Tag::class, NoteTagCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun  tagsDao(): TagsDao

    companion object {
        fun getInstance(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "notes-database").build()
    }
}