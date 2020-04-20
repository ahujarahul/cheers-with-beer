package com.rahulahuja.cheerswithbeer.business.`interface`

import com.rahulahuja.cheerswithbeer.data.NetworkResult
import com.rahulahuja.cheerswithbeer.presentation.models.BeerEntity
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity

/**
 * Created by rahulahuja on 20/04/20.
 */
interface BeersRepository {
    suspend fun getAllBeers(): NetworkResult<BeersEntity>?
    fun saveBeer(beer: BeerEntity): Boolean
    fun removeBeer(id: Int): Boolean
    fun getFavouriteBeers(): BeersEntity
}