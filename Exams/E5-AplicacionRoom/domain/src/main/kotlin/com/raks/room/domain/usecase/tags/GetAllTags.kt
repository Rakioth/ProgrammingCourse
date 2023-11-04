package com.raks.room.domain.usecase.tags

import com.raks.room.domain.model.TagDto
import com.raks.room.domain.repository.TagsRepository

class GetAllTags(
    private val repository: TagsRepository
) {

    suspend operator fun invoke(): List<TagDto> =
        repository.getAllTags()

}