package com.raks.room.data.mapper

import com.raks.room.data.embedded.NoteWithTags
import com.raks.room.domain.model.NoteWithTagsDto

fun NoteWithTags.toDomain()    = NoteWithTagsDto(
    note = note.toDomain(),
    tags = tags.map { it.toDomain() },
)

fun NoteWithTagsDto.toEntity() = NoteWithTags(
    note = note.toEntity(),
    tags = tags.map { it.toEntity() },
)