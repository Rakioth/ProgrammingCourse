package com.raks.room.presentation.notes.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.raks.room.databinding.ItemNoteBinding
import com.raks.room.domain.model.NoteDto

class NoteAdapter(
    private val noteClickListener: NoteClickListener
) : ListAdapter<NoteDto, NoteViewHolder>(NoteDiffUtil) {

    interface NoteClickListener {
        fun onNoteEdit(nid: Int)
        fun onNoteDelete(note: NoteDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, noteClickListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(getItem(position))

}