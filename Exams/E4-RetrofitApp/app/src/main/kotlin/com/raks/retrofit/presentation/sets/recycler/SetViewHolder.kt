package com.raks.retrofit.presentation.sets.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raks.retrofit.databinding.ItemSetBinding
import com.raks.retrofit.domain.model.Set

class SetViewHolder(
    private val binding:          ItemSetBinding,
    private val setClickListener: SetAdapter.SetClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(set: Set) = with(binding) {
        Glide.with(setLogo.context)
            .load(set.images.logo)
            .into(setLogo)
        Glide.with(setSymbol.context)
            .load(set.images.symbol)
            .into(setSymbol)
        setName.text = set.name
        setView.setOnClickListener { setClickListener.onSetClick(set) }
    }

}