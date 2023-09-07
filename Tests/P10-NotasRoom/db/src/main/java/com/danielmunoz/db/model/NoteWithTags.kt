package com.danielmunoz.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.danielmunoz.db.entity.Note
import com.danielmunoz.db.entity.NoteTagCrossRef
import com.danielmunoz.db.entity.Tag

data class NoteWithTags(
    @Embedded
    val note: Note,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "tagId",
        associateBy  = Junction(NoteTagCrossRef::class)
    )
    val tags: List<Tag>
)