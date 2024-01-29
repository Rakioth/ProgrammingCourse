package com.raks.room.data.mapper

import com.raks.room.data.entity.Note
import com.raks.room.domain.model.NoteDto

fun Note.toDomain()    = NoteDto(
    nid   = nid,
    title = title,
    body  = body,
)

fun NoteDto.toEntity() = Note(
    nid   = nid,
    title = title,
    body  = body,
)