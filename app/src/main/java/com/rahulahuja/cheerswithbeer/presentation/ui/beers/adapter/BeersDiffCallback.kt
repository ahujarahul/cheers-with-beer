package com.rahulahuja.cheerswithbeer.presentation.ui.beers.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel

/**
 * Created by rahulahuja on 16/04/20.
 */
class BeersDiffCallback(private val oldBeers: List<BeerAdapterModel>,
                        private val newBeers: List<BeerAdapterModel>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldBeers[oldItemPosition].id == newBeers[newItemPosition].id

    override fun getOldListSize(): Int = oldBeers.size

    override fun getNewListSize(): Int = newBeers.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldBeers[oldItemPosition] == newBeers[newItemPosition]
}