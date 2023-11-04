package com.raks.retrofit.presentation.cards.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.raks.retrofit.databinding.ItemCardBinding
import com.raks.retrofit.domain.model.Card

class CardAdapter(
    private val cardClickListener: CardClickListener
) : ListAdapter<Card, CardViewHolder>(CardDiffUtil) {

    interface CardClickListener {
        fun onCardClick(card: Card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding, cardClickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(getItem(position))

}