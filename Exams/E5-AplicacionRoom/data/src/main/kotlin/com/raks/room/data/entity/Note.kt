package com.raks.room.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val nid:   Int,
    val title: String,
    val body:  String,
)