package com.raks.room.presentation.tags

import com.raks.room.domain.model.TagDto

sealed class TagsEvent {
    data class SaveTag(val tag: TagDto)   : TagsEvent()
    data class DeleteTag(val tag: TagDto) : TagsEvent()
}