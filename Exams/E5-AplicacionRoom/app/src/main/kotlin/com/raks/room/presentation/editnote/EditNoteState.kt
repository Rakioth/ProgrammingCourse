package com.raks.room.presentation.editnote

import com.raks.room.domain.model.NoteWithTagsDto
import com.raks.room.domain.model.TagDto

data class EditNoteState(
    val noteWithTags: NoteWithTagsDto = NoteWithTagsDto.empty(),
    val tags: List<TagDto>            = emptyList(),
)