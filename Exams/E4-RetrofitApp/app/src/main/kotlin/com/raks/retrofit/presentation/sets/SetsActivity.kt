package com.raks.retrofit.presentation.sets

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raks.retrofit.databinding.ActivitySetsBinding
import com.raks.retrofit.domain.model.Set
import com.raks.retrofit.presentation.cards.CardsActivity
import com.raks.retrofit.presentation.sets.recycler.SetAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SetsActivity : AppCompatActivity() {

    private val viewModel by viewModels<SetsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySetsBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val adapter                = buildAdapter()
            recyclerSets.layoutManager = LinearLayoutManager(this@SetsActivity)
            recyclerSets.adapter       = adapter

            recyclerSets.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1))
                        viewModel.onEvent(SetsEvent.LoadMore)
                }

            })

            viewModel.onEvent(SetsEvent.LoadMore)

            lifecycleScope.launch {
                viewModel.state.collect { adapter.submitList(it.sets) }
            }
        }
    }

    private fun buildAdapter() =
        SetAdapter(object : SetAdapter.SetClickListener {

            override fun onSetClick(set: Set) =
                startActivity(
                    Intent(this@SetsActivity, CardsActivity::class.java)
                        .apply { putExtra(CardsActivity.SET_ID_KEY, set.id) }
                )

        })

}