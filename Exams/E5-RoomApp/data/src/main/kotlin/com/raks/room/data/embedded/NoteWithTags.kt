package com.raks.room.data.embedded

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.raks.room.data.entity.Note
import com.raks.room.data.entity.NoteTagCrossRef
import com.raks.room.data.entity.Tag

data class NoteWithTags(
    @Embedded
    val note: Note,
    @Relation(
        parentColumn = "nid",
        entityColumn = "tid",
        associateBy  = Junction(NoteTagCrossRef::class),
    )
    val tags: List<Tag>,
)