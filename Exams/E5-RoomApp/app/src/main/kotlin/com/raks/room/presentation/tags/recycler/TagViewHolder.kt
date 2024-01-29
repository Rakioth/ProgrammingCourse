package com.raks.room.presentation.tags.recycler

import androidx.recyclerview.widget.RecyclerView
import com.raks.room.databinding.ItemTagBinding
import com.raks.room.domain.model.TagWithNotesDto

class TagViewHolder(
    private val binding:          ItemTagBinding,
    private val tagClickListener: TagAdapter.TagClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(tagWithNotes: TagWithNotesDto) = with(binding) {
        tagName.setText(tagWithNotes.tag.tag)
        tagRef.text = tagWithNotes.notes.size.toString()
        buttonSaveTag.setOnClickListener   { tagClickListener.onTagSave(tagWithNotes.tag, tagName.text.toString()) }
        buttonDeleteTag.setOnClickListener { tagClickListener.onTagDelete(tagWithNotes.tag)                        }
    }

}