package com.raks.room.data.mapper

import com.raks.room.data.embedded.TagWithNotes
import com.raks.room.domain.model.TagWithNotesDto

fun TagWithNotes.toDomain()    = TagWithNotesDto(
    tag   = tag.toDomain(),
    notes = notes.map { it.toDomain() },
)

fun TagWithNotesDto.toEntity() = TagWithNotes(
    tag   = tag.toEntity(),
    notes = notes.map { it.toEntity() },
)