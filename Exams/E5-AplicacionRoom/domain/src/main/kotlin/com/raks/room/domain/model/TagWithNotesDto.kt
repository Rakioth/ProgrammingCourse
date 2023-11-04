package com.raks.room.domain.model

data class TagWithNotesDto(
    val tag:   TagDto,
    val notes: List<NoteDto>,
) {

    companion object {
        fun empty() = TagWithNotesDto(TagDto.empty(), emptyList())
    }

}