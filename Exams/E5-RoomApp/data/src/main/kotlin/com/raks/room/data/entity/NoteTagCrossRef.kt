package com.raks.room.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName   = "note_tag_ref",
    primaryKeys = ["nid", "tid"],
)
data class NoteTagCrossRef(
    val nid: Int,
    @ColumnInfo(index = true)
    val tid: Int,
)