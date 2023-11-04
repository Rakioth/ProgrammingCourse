package com.raks.room.presentation.editnote

import com.raks.room.domain.model.NoteWithTagsDto

sealed class EditNoteEvent {
    data class LoadInfo(val nid: Int)                              : EditNoteEvent()
    data class SaveNoteWithTags(val noteWithTags: NoteWithTagsDto) : EditNoteEvent()
}