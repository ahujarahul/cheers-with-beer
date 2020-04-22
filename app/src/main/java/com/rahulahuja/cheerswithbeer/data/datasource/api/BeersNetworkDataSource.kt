package com.rahulahuja.cheerswithbeer.data.datasource.api

import com.rahulahuja.cheerswithbeer.data.datasource.api.customException.handleNetworkExceptions
import com.rahulahuja.cheerswithbeer.data.datasource.api.models.BeerApi
import com.rahulahuja.cheerswithbeer.data.datasource.api.network.BeersApiService
import com.rahulahuja.cheerswithbeer.utils.NetworkResult
import java.lang.Exception

/**
 * Created by rahulahuja on 21/04/20.
 */
const val MAX_RESULTS_PER_PAGE: Int = 80

class BeersNetworkDataSource(
    private val beersApiService: BeersApiService) {

    suspend fun getAllBeers(page: String): NetworkResult<List<BeerApi>> {
        return try {
            val beers = beersApiService.getAllBeers(page, MAX_RESULTS_PER_PAGE.toString())
            NetworkResult.success(beers)
        } catch (exception: Exception) {
            NetworkResult.error(handleNetworkExceptions(exception))
        }
    }
}