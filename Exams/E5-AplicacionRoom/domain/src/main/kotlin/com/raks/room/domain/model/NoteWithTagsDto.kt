package com.raks.room.domain.model

data class NoteWithTagsDto(
    val note: NoteDto,
    val tags: List<TagDto>,
) {

    companion object {
        fun empty() = NoteWithTagsDto(NoteDto.empty(), emptyList())
    }

}