package com.rahulahuja.cheerswithbeer.data.datasource.api.network

import com.rahulahuja.cheerswithbeer.data.datasource.api.models.BeerApi
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rahulahuja on 22/04/20.
 */
interface BeersApiService {
    @GET("beers?")

    suspend fun getAllBeers(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): List<BeerApi>?
}