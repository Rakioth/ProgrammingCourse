package com.raks.retrofit.presentation.sets.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.raks.retrofit.databinding.ItemSetBinding
import com.raks.retrofit.domain.model.Set

class SetAdapter(
    private val setClickListener: SetClickListener
) : ListAdapter<Set, SetViewHolder>(SetDiffUtil) {

    interface SetClickListener {
        fun onSetClick(set: Set)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val binding = ItemSetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SetViewHolder(binding, setClickListener)
    }

    override fun onBindViewHolder(holder: SetViewHolder, position: Int) =
        holder.bind(getItem(position))

}