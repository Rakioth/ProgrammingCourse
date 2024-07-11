package com.raks.room.domain.usecase.tags

import com.raks.room.domain.model.TagWithNotesDto
import com.raks.room.domain.repository.TagsRepository
import kotlinx.coroutines.flow.Flow

class GetTagsWithNotes(
    private val repository: TagsRepository
) {

    operator fun invoke(): Flow<List<TagWithNotesDto>> =
        repository.getTagsWithNotes()

}