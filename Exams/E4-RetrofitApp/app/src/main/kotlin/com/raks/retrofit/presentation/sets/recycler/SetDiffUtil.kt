package com.raks.retrofit.presentation.sets.recycler

import androidx.recyclerview.widget.DiffUtil
import com.raks.retrofit.domain.model.Set

object SetDiffUtil : DiffUtil.ItemCallback<Set>() {

    override fun areItemsTheSame(oldItem: Set, newItem: Set)    =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Set, newItem: Set) =
        oldItem == newItem

}