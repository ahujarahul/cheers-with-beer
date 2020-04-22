package com.rahulahuja.cheerswithbeer.data.datasource.cache

import com.rahulahuja.cheerswithbeer.data.datasource.models.LocalBeerEntity

/**
 * Created by rahulahuja on 21/04/20.
 */
class BeersCacheDataSource {
    private val beersMutableList: MutableList<LocalBeerEntity> = mutableListOf()
    var beers: List<LocalBeerEntity>
        get() = beersMutableList
        set(value) {
            beersMutableList.clear()
            beersMutableList.addAll(value)
        }
}