package com.raks.retrofit.presentation.cards

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raks.retrofit.databinding.ActivityCardsBinding
import com.raks.retrofit.domain.model.Card
import com.raks.retrofit.presentation.carddetails.CardDetailsActivity
import com.raks.retrofit.presentation.cards.recycler.CardAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardsActivity : AppCompatActivity() {

    companion object {
        const val SET_ID_KEY = "SET_ID"
    }

    private val viewModel by viewModels<CardsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCardsBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val sid                     = intent.getStringExtra(SET_ID_KEY).toString()
            val adapter                 = buildAdapter()
            recyclerCards.layoutManager = GridLayoutManager(this@CardsActivity, 3)
            recyclerCards.adapter       = adapter

            recyclerCards.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1))
                        viewModel.onEvent(CardsEvent.LoadMore(sid))
                }

            })

            viewModel.onEvent(CardsEvent.LoadMore(sid))

            lifecycleScope.launch {
                viewModel.state.collect { adapter.submitList(it.cards) }
            }
        }
    }

    private fun buildAdapter() =
        CardAdapter(object : CardAdapter.CardClickListener {

            override fun onCardClick(card: Card) =
                startActivity(
                    Intent(this@CardsActivity, CardDetailsActivity::class.java)
                        .apply { putExtra(CardDetailsActivity.CARD_ID_KEY, card.id) }
                )

        })

}