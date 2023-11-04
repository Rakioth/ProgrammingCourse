package com.raks.retrofit.presentation.cards.recycler

import androidx.recyclerview.widget.DiffUtil
import com.raks.retrofit.domain.model.Card

object CardDiffUtil : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card)    =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Card, newItem: Card) =
        oldItem == newItem

}