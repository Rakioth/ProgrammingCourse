package com.raks.room.domain.usecase

import com.raks.room.domain.usecase.tags.*

data class TagsUseCases(
    val getAllTags:                  GetAllTags,
    val getTagsWithNotes:            GetTagsWithNotes,
    val insertTag:                   InsertTag,
    val updateTag:                   UpdateTag,
    val deleteTagAndCrossReferences: DeleteTagAndCrossReferences,
)