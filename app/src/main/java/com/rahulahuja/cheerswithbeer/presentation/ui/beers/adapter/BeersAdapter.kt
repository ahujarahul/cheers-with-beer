package com.rahulahuja.cheerswithbeer.presentation.ui.beers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel
import com.rahulahuja.cheerswithbeer.presentation.ui.beers.BeersFragmentCallback
import com.rahulahuja.cheerswithbeer.utils.inflate
import kotlinx.android.synthetic.main.item_beer_list.view.*

/**
 * Created by rahulahuja on 16/04/20.
 */
class BeersAdapter(private val beersFragmentCallback: BeersFragmentCallback):
    RecyclerView.Adapter<BeersViewHolder>() {

    private lateinit var beers: List<BeerAdapterModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeersViewHolder {
        val view = parent.inflate(R.layout.item_beer_list)
        val viewHolder = BeersViewHolder(view)

        setFavoriteBeerListener(viewHolder)
        setBeerItemListener(viewHolder)

        return viewHolder
    }

    override fun onBindViewHolder(beersViewHolder: BeersViewHolder, position: Int) {
        beersViewHolder.apply {
            populateViews(beers[position])
        }
    }

    override fun getItemCount() = beers.size

    private fun setBeerItemListener(viewHolder: BeersViewHolder) {
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition

            if (position != RecyclerView.NO_POSITION) {
//                beerDetailListener.invoke(beers[position])
                beersFragmentCallback.onSetBeerDetailListener(beers[position])
            }
        }
    }

    private fun setFavoriteBeerListener(viewHolder: BeersViewHolder) {
        viewHolder.itemView.iv_item_beer_list_favorite.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val beer = beers[position].apply {
                    isFavorite = !isFavorite
                }
//                favoriteBeerListener.invoke(beer)
                beersFragmentCallback.onSetFavoriteBeerListener(beer)
            }
        }
    }

    fun updateAdapter(updatedList: List<BeerAdapterModel>) {
        val result = DiffUtil.calculateDiff(BeersDiffCallback(beers, updatedList))
        beers = updatedList.toMutableList()
        result.dispatchUpdatesTo(this)
    }

    fun setData(beers: List<BeerAdapterModel>) {
        this.beers = beers
    }
}