package com.raks.room.data.mapper

import com.raks.room.data.entity.Tag
import com.raks.room.domain.model.TagDto

fun Tag.toDomain()    = TagDto(
    tid = tid,
    tag = tag,
)

fun TagDto.toEntity() = Tag(
    tid = tid,
    tag = tag,
)