package com.raks.room.presentation.notes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raks.room.R
import com.raks.room.databinding.ActivityNotesBinding
import com.raks.room.databinding.ItemSwitchBinding
import com.raks.room.domain.model.NoteDto
import com.raks.room.presentation.editnote.EditNoteActivity
import com.raks.room.presentation.notes.recycler.NoteAdapter
import com.raks.room.presentation.tags.TagsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesActivity : AppCompatActivity() {

    private val viewModel by viewModels<NotesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityNotesBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val adapter                 = buildAdapter()
            recyclerNotes.layoutManager = LinearLayoutManager(this@NotesActivity)
            recyclerNotes.adapter       = adapter

            lifecycleScope.launch {
                viewModel.state.collect { adapter.submitList(it) }
            }
        }
    }

    private fun buildAdapter() =
        NoteAdapter(object : NoteAdapter.NoteClickListener {

            override fun onNoteEdit(nid: Int) =
                startActivity(
                    Intent(this@NotesActivity, EditNoteActivity::class.java)
                        .apply { putExtra(EditNoteActivity.NOTE_ID_KEY, nid) }
                )

            override fun onNoteDelete(note: NoteDto) =
                viewModel.onEvent(NotesEvent.DeleteNote(note))

        })

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notes, menu)

        val menuSwitch = menu?.findItem(R.id.switch_theme)
        menuSwitch?.setActionView(R.layout.item_switch)

        ItemSwitchBinding.bind(menuSwitch?.actionView!!).apply {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                switchButton.isChecked = true
                switchIcon.setImageResource(R.drawable.ic_baseline_nightlight_24)
            }

            switchButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    switchIcon.setImageResource(R.drawable.ic_baseline_wb_sunny_24)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    switchIcon.setImageResource(R.drawable.ic_baseline_nightlight_24)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.add_note    -> {
            startActivity(
                Intent(this@NotesActivity, EditNoteActivity::class.java)
                    .apply { putExtra(EditNoteActivity.NOTE_ID_KEY, 0) }
            )
            true
        }

        R.id.manage_tags -> {
            startActivity(
                Intent(this@NotesActivity, TagsActivity::class.java)
            )
            true
        }

        else             -> super.onOptionsItemSelected(item)
    }

}