package com.rahulahuja.cheerswithbeer.presentation.ui.beers.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel
import com.rahulahuja.cheerswithbeer.utils.applyBackgroundColor
import com.rahulahuja.cheerswithbeer.utils.loadImage
import kotlinx.android.synthetic.main.item_beer_list.view.*

/**
 * Created by rahulahuja on 16/04/20.
 */
class BeersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateViews(beer: BeerAdapterModel) {
        itemView.tv_item_list_beer_abv.text = getAbv(beer.abv.toString())
        itemView.tv_item_list_beer_abv.applyBackgroundColor(beer.abvColor)
        itemView.tv_item_list_beer_name.text = beer.name
        itemView.tv_item_beer_list_tagline.text = beer.tagline
        itemView.iv_item_beer_list_image.loadImage(beer.image)
        populateFavoriteIconView(beer.isFavorite)
    }

    private fun populateFavoriteIconView(isFavorite: Boolean) {
        getFavoriteIcon(isFavorite)?.let {
            itemView.iv_item_beer_list_favorite.setImageDrawable(it)
        }
    }

    private fun getFavoriteIcon(isFavorite: Boolean): Drawable? {
        return if (isFavorite) {
            itemView.context.getDrawable(R.drawable.ic_star_white_24dp)
        } else {
            itemView.context.getDrawable(R.drawable.ic_star_border_white_24dp)
        }
    }

    private fun getAbv(abvId: String) = itemView.context.getString(R.string.abv, abvId)
}