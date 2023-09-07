package com.danielmunoz.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.danielmunoz.db.entity.Note
import com.danielmunoz.db.entity.NoteTagCrossRef
import com.danielmunoz.db.entity.Tag

data class TagWithNotes(
    @Embedded
    val tag: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "noteId",
        associateBy  = Junction(NoteTagCrossRef::class)
    )
    val notes: List<Note>
)