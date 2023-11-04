package com.raks.room.data.di

import android.app.Application
import androidx.room.Room
import com.raks.room.data.NotwayDatabase
import com.raks.room.data.repository.*
import com.raks.room.domain.repository.*
import com.raks.room.domain.usecase.*
import com.raks.room.domain.usecase.notes.*
import com.raks.room.domain.usecase.tags.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNotwayDatabase(app: Application):           NotwayDatabase  =
        Room.databaseBuilder(app, NotwayDatabase::class.java, NotwayDatabase.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideNotesRepository(db: NotwayDatabase):        NotesRepository =
        NotesRepositoryImpl(db.notwayDao())

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NotesRepository): NotesUseCases   =
        NotesUseCases(
            GetAllNotes(repository),
            FindNoteWithTags(repository),
            InsertNoteWithTags(repository),
            UpdateNoteWithTags(repository),
            DeleteNoteAndCrossReferences(repository),
        )

    @Provides
    @Singleton
    fun provideTagsRepository(db: NotwayDatabase):         TagsRepository  =
        TagsRepositoryImpl(db.notwayDao())

    @Provides
    @Singleton
    fun provideTagsUseCases(repository: TagsRepository):   TagsUseCases    =
        TagsUseCases(
            GetAllTags(repository),
            GetTagsWithNotes(repository),
            InsertTag(repository),
            UpdateTag(repository),
            DeleteTagAndCrossReferences(repository),
        )

}