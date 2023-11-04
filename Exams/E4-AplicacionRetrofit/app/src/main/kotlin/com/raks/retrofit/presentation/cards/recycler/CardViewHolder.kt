package com.raks.retrofit.presentation.cards.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raks.retrofit.databinding.ItemCardBinding
import com.raks.retrofit.domain.model.Card

class CardViewHolder(
    private val binding:           ItemCardBinding,
    private val cardClickListener: CardAdapter.CardClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(card: Card) = with(binding) {
        Glide.with(cardImageSmall.context)
            .load(card.images.small)
            .into(cardImageSmall)
        cardImageSmall.setOnClickListener { cardClickListener.onCardClick(card) }
    }

}