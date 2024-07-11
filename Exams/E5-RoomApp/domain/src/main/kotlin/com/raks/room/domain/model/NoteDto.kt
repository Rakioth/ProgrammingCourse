package com.raks.room.domain.model

data class NoteDto(
    val nid:   Int,
    val title: String,
    val body:  String,
) {

    companion object {
        fun empty() = NoteDto(0, "", "")
    }

}