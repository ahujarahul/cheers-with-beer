package com.rahulahuja.cheerswithbeer.presentation.ui.beers

import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel

/**
 * Created by rahulahuja on 16/04/20.
 */
interface BeersFragmentCallback {
    fun onSetBeerDetailListener(beerAdapterModel: BeerAdapterModel)
    fun onSetFavoriteBeerListener(beerAdapterModel: BeerAdapterModel)
}