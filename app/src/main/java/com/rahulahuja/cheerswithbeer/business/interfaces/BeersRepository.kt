package com.rahulahuja.cheerswithbeer.business.interfaces

import com.rahulahuja.cheerswithbeer.utils.NetworkResult
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