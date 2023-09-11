package com.danielmunoz.android.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielmunoz.android.R
import com.danielmunoz.db.entity.Note

class NoteAdapter(
    private val notes: List<Note>,
    private val noteClickListener: NoteClickListener
) : RecyclerView.Adapter<NoteViewHolder>() {

    interface NoteClickListener {
        fun onNoteEdit(position: Int)
        fun onNoteDelete(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view, noteClickListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.bind(notes[position])

    override fun getItemCount(): Int = notes.size

}