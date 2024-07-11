package com.raks.room.presentation.notes.recycler

import androidx.recyclerview.widget.DiffUtil
import com.raks.room.domain.model.NoteDto

object NoteDiffUtil : DiffUtil.ItemCallback<NoteDto>() {

    override fun areItemsTheSame(oldItem: NoteDto, newItem: NoteDto)    =
        oldItem.nid == newItem.nid

    override fun areContentsTheSame(oldItem: NoteDto, newItem: NoteDto) =
        oldItem == newItem

}