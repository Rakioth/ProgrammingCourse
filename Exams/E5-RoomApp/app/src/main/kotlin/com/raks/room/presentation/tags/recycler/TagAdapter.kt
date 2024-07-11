package com.raks.room.presentation.tags.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.raks.room.databinding.ItemTagBinding
import com.raks.room.domain.model.TagDto
import com.raks.room.domain.model.TagWithNotesDto

class TagAdapter(
    private val tagClickListener: TagClickListener
) : ListAdapter<TagWithNotesDto, TagViewHolder>(TagDiffUtil) {

    interface TagClickListener {
        fun onTagSave(tag: TagDto, name: String)
        fun onTagDelete(tag: TagDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding, tagClickListener)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun addBlankItem() {
        val list = currentList.toMutableList()
        list.add(TagWithNotesDto.empty())
        submitList(list)
    }

}