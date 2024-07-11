package com.raks.room.domain.repository

import com.raks.room.domain.model.TagDto
import com.raks.room.domain.model.TagWithNotesDto
import kotlinx.coroutines.flow.Flow

interface TagsRepository {

            fun getTagsWithNotes():                       Flow<List<TagWithNotesDto>>

    suspend fun getAllTags():                             List<TagDto>

    suspend fun insertTag(tag: TagDto):                   Int

    suspend fun updateTag(tag: TagDto)

    suspend fun deleteTagAndCrossReferences(tag: TagDto)

}