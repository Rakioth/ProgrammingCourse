package com.raks.room.data.embedded

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.raks.room.data.entity.Note
import com.raks.room.data.entity.NoteTagCrossRef
import com.raks.room.data.entity.Tag

data class TagWithNotes(
    @Embedded
    val tag:   Tag,
    @Relation(
        parentColumn = "tid",
        entityColumn = "nid",
        associateBy  = Junction(NoteTagCrossRef::class),
    )
    val notes: List<Note>,
)