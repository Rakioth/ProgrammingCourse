package com.iothar.android.recycler.cards

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.R
import com.iothar.android.api.model.Cards

class CardsViewHolder(
    view: View,
    private val onCardsClickListener: CardsAdapter.OnCardsClickListener
) : RecyclerView.ViewHolder(view) {

    private val _image = view.findViewById<ImageButton>(R.id.card_image_small)

    fun bind(cards: Cards) {
        _image.setOnClickListener { onCardsClickListener.onCardsClick(cards) }
        Glide.with(_image.context)
            .load(cards.images.small)
            .into(_image)
    }

}