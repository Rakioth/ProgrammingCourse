package com.raks.room.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raks.room.data.dao.NotwayDao
import com.raks.room.data.entity.*

@Database(
    entities     = [Note::class, Tag::class, NoteTagCrossRef::class],
    version      = 1,
    exportSchema = false,
)
abstract class NotwayDatabase : RoomDatabase() {

    abstract fun notwayDao(): NotwayDao

    companion object {
        const val DATABASE_NAME = "notway-database"
    }

}