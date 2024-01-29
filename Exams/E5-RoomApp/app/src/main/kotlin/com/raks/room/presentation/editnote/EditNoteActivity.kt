package com.raks.room.presentation.editnote

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import com.google.android.material.chip.Chip
import com.raks.room.R
import com.raks.room.databinding.ActivityEditNoteBinding
import com.raks.room.databinding.ItemChipBinding
import com.raks.room.domain.model.NoteDto
import com.raks.room.domain.model.NoteWithTagsDto
import com.raks.room.domain.model.TagDto
import com.raks.room.presentation.dialog.NewTagDialog
import com.raks.room.presentation.notes.NotesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class EditNoteActivity : AppCompatActivity(), NewTagDialog.NewTagDialogListener {

    companion object {
        const val NOTE_ID_KEY = "NOTE_ID"
    }

    private lateinit var binding: ActivityEditNoteBinding

    private var nid by Delegates.notNull<Int>()

    private val viewModel by viewModels<EditNoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        nid     = intent.getIntExtra(NOTE_ID_KEY, 0)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = if (nid == 0)
            getString(R.string.title_add_note) else
            getString(R.string.title_edit_note)

        binding.apply {
            setContentView(root)

            viewModel.onEvent(EditNoteEvent.LoadInfo(nid))

            lifecycleScope.launch {
                viewModel.state.collect { bind(it.noteWithTags, it.tags) }
            }
        }
    }

    private fun save() = with(binding) {
        val noteWithTags = NoteWithTagsDto(
            note = NoteDto(
                nid   = nid,
                title = noteTitle.text.toString(),
                body  = noteBody.text.toString(),
            ),
            tags = noteTags.children.mapNotNull {
                val chip = it as Chip
                if (chip.isChecked) TagDto(chip.id, chip.text.toString()) else null
            }.toList(),
        )

        viewModel.onEvent(EditNoteEvent.SaveNoteWithTags(noteWithTags))

        startActivity(
            Intent(this@EditNoteActivity, NotesActivity::class.java)
        )
    }

    private fun bind(noteWithTags: NoteWithTagsDto, tags: List<TagDto>) = with(binding) {
        noteTags.removeAllViews()

        tags.forEach {
            val chip       = ItemChipBinding.inflate(layoutInflater, noteTags, false).root
            chip.id        = it.tid
            chip.text      = it.tag
            chip.isChecked = noteWithTags.tags.map { tag -> tag.tid }.contains(chip.id)
            noteTags.addView(chip)
        }

        noteTitle.setText(noteWithTags.note.title)
        noteBody.setText(noteWithTags.note.body)
    }

    override fun onDialogOk(newTag: String) = with(binding) {
        if (newTag.isBlank()) return

        val chip       = ItemChipBinding.inflate(layoutInflater, noteTags, false).root
        chip.id        = 0
        chip.text      = newTag
        chip.isChecked = true
        noteTags.addView(chip)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }

        R.id.add_tag      -> {
            NewTagDialog().show(supportFragmentManager, "dialog")
            true
        }

        R.id.save_note    -> {
            save()
            true
        }

        else              -> super.onOptionsItemSelected(item)
    }

}