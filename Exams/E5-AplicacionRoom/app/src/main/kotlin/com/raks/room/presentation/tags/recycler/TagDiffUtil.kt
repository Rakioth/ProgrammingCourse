package com.raks.room.presentation.tags.recycler

import androidx.recyclerview.widget.DiffUtil
import com.raks.room.domain.model.TagWithNotesDto

object TagDiffUtil : DiffUtil.ItemCallback<TagWithNotesDto>() {

    override fun areItemsTheSame(oldItem: TagWithNotesDto, newItem: TagWithNotesDto)    =
        oldItem.tag.tid == newItem.tag.tid

    override fun areContentsTheSame(oldItem: TagWithNotesDto, newItem: TagWithNotesDto) =
        oldItem == newItem

}