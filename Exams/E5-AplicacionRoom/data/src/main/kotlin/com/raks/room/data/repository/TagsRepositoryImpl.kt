package com.raks.room.data.repository

import com.raks.room.data.dao.NotwayDao
import com.raks.room.data.mapper.toDomain
import com.raks.room.data.mapper.toEntity
import com.raks.room.domain.model.TagDto
import com.raks.room.domain.model.TagWithNotesDto
import com.raks.room.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TagsRepositoryImpl(
    private val dao: NotwayDao
) : TagsRepository {

    override         fun getTagsWithNotes():                       Flow<List<TagWithNotesDto>> =
        dao.getTagsWithNotes().map { tags -> tags.map { it.toDomain() } }

    override suspend fun getAllTags():                             List<TagDto>                =
        dao.getAllTags().map { it.toDomain() }

    override suspend fun insertTag(tag: TagDto):                   Int                         =
        dao.insertTag(tag.toEntity()).toInt()

    override suspend fun updateTag(tag: TagDto)                                                =
        dao.updateTag(tag.toEntity())

    override suspend fun deleteTagAndCrossReferences(tag: TagDto)                              =
        dao.deleteTagAndCrossReferences(tag.toEntity())

}