package com.raks.room.presentation.tags

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.raks.room.R
import com.raks.room.databinding.ActivityTagsBinding
import com.raks.room.domain.model.TagDto
import com.raks.room.presentation.tags.recycler.TagAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TagsActivity : AppCompatActivity() {

    private lateinit var adapter: TagAdapter

    private val viewModel by viewModels<TagsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = getString(R.string.edit_tags)

        ActivityTagsBinding.inflate(layoutInflater).apply {
            setContentView(root)

            adapter                    = buildAdapter()
            recyclerTags.layoutManager = LinearLayoutManager(this@TagsActivity)
            recyclerTags.adapter       = adapter

            lifecycleScope.launch {
                viewModel.state.collect { adapter.submitList(it) }
            }
        }
    }

    private fun buildAdapter() =
        TagAdapter(object : TagAdapter.TagClickListener {

            override fun onTagSave(tag: TagDto, name: String) =
                viewModel.onEvent(TagsEvent.SaveTag(TagDto(tag.tid, name)))

            override fun onTagDelete(tag: TagDto) =
                viewModel.onEvent(TagsEvent.DeleteTag(tag))

        })

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tags, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }

        R.id.new_tag      -> {
            adapter.addBlankItem()
            true
        }

        else              -> super.onOptionsItemSelected(item)
    }

}