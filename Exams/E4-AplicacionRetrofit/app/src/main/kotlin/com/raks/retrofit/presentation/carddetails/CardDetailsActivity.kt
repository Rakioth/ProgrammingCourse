package com.raks.retrofit.presentation.carddetails

import android.animation.LayoutTransition
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.raks.retrofit.R
import com.raks.retrofit.databinding.ActivityCardDetailsBinding
import com.raks.retrofit.domain.model.CardDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardDetailsActivity : AppCompatActivity() {

    companion object {
        const val CARD_ID_KEY = "CARD_ID"
    }

    private lateinit var binding: ActivityCardDetailsBinding

    private val viewModel by viewModels<CardDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCardDetailsBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            cardLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            cardView.setOnClickListener { viewModel.onEvent(CardDetailsEvent.ToggleInfo) }

            viewModel.onEvent(CardDetailsEvent.LoadInfo(intent.getStringExtra(CARD_ID_KEY).toString()))

            lifecycleScope.launch {
                viewModel.state.collect {
                    toggle(it.isVisible)
                    it.cardDetails?.let { cardDetails -> bind(cardDetails.data) }
                }
            }
        }
    }

    private fun toggle(isVisible: Boolean) = with(binding) {
        val visibility = if (isVisible) View.VISIBLE else View.GONE
        TransitionManager.beginDelayedTransition(cardLayout, AutoTransition())
        cardInfoDetails.visibility   = visibility
        cardMarket.visibility        = visibility
        cardMarketDetails.visibility = visibility
    }

    private fun bind(cardDetails: CardDetails.Card) = with(binding) {
        Glide.with(cardImageLarge.context)
            .load(cardDetails.images.large)
            .into(cardImageLarge)
        cardInfoDetails.text   = getString(
            R.string.card_info_details,
            cardDetails.number,
            cardDetails.name,
            cardDetails.rarity,
            cardDetails.flavorText ?: getString(R.string.default_flavor)
        )
        cardMarketDetails.text = getString(
            R.string.card_market_details,
            DecimalFormat.getCurrencyInstance().format(cardDetails.cardmarket.prices.lowPrice),
            DecimalFormat.getCurrencyInstance().format(cardDetails.cardmarket.prices.trendPrice),
            DecimalFormat.getCurrencyInstance().format(cardDetails.cardmarket.prices.averageSellPrice),
            cardDetails.cardmarket.updatedAt
        )
    }

}