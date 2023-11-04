package com.raks.room.presentation.notes.recycler

import androidx.recyclerview.widget.RecyclerView
import com.raks.room.databinding.ItemNoteBinding
import com.raks.room.domain.model.NoteDto

class NoteViewHolder(
    private val binding:           ItemNoteBinding,
    private val noteClickListener: NoteAdapter.NoteClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: NoteDto) = with(binding) {
        noteName.text = note.title
        buttonEditNote.setOnClickListener   { noteClickListener.onNoteEdit(note.nid) }
        buttonDeleteNote.setOnClickListener { noteClickListener.onNoteDelete(note)   }
    }

}