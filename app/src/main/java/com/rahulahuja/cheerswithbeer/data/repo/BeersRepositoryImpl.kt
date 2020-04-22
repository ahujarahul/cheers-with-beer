package com.rahulahuja.cheerswithbeer.data.repo

import com.rahulahuja.cheerswithbeer.business.interfaces.BeersRepository
import com.rahulahuja.cheerswithbeer.data.datasource.api.BeersNetworkDataSource
import com.rahulahuja.cheerswithbeer.data.datasource.api.MAX_RESULTS_PER_PAGE
import com.rahulahuja.cheerswithbeer.data.datasource.api.customException.BadRequestException
import com.rahulahuja.cheerswithbeer.data.datasource.api.models.BeerApi
import com.rahulahuja.cheerswithbeer.data.datasource.cache.BeersCacheDataSource
import com.rahulahuja.cheerswithbeer.data.datasource.local.FavoritesLocalDataSource
import com.rahulahuja.cheerswithbeer.data.datasource.models.LocalBeerEntity
import com.rahulahuja.cheerswithbeer.data.mapper.ApiToEntityMapper
import com.rahulahuja.cheerswithbeer.enums.ResultType
import com.rahulahuja.cheerswithbeer.data.mapper.ApiToLocalModelMapper
import com.rahulahuja.cheerswithbeer.data.mapper.EntityToLocalMapper
import com.rahulahuja.cheerswithbeer.data.mapper.LocalToEntityMapper
import com.rahulahuja.cheerswithbeer.presentation.models.BeerEntity
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity
import com.rahulahuja.cheerswithbeer.utils.NetworkResult

/**
 * Created by rahulahuja on 21/04/20.
 */
class BeersRepositoryImpl(
    private val beersNetworkDataSource: BeersNetworkDataSource,
    private val favoritesLocalDataSource: FavoritesLocalDataSource,
    private val beersCacheDataSource: BeersCacheDataSource
) : BeersRepository {

    override suspend fun getAllBeers(): NetworkResult<BeersEntity>? {
        var page = -1
        var result: NetworkResult<BeersEntity>? = null
        val mutableBeers: MutableList<BeerApi> = mutableListOf()

        val allBeers: List<LocalBeerEntity> = beersCacheDataSource.beers
        if (allBeers.isNotEmpty()) return NetworkResult.success(LocalToEntityMapper.map(allBeers))

        do {
            page = getPageToCheckBeers(page, mutableBeers.isNotEmpty(), mutableBeers.size)

            beersNetworkDataSource.getAllBeers(page.toString()).let { resultListBeerResponse ->
                if (resultListBeerResponse.resultType == ResultType.SUCCESS) {
                    resultListBeerResponse.data?.let {
                        mutableBeers.addAll(resultListBeerResponse.data)
                    }
                }

                result = if (resultListBeerResponse.resultType == ResultType.SUCCESS ||
                    (resultListBeerResponse.error is BadRequestException && mutableBeers.isNotEmpty())) {
                    NetworkResult.success(ApiToEntityMapper.map(mutableBeers.toList()))
                } else {
                    NetworkResult.error(resultListBeerResponse.error)
                }
            }
        } while (result?.resultType != NetworkResult.error<Error>().resultType && page != -1)

        if (result?.resultType == ResultType.SUCCESS) beersCacheDataSource.beers = ApiToLocalModelMapper.map(mutableBeers.toList())

        return result
    }

    private fun getPageToCheckBeers(currentPage: Int, isMutableBeersNotEmpty: Boolean, beersSize: Int): Int {
        var page: Int = currentPage
        if (isMutableBeersNotEmpty) {
            if (isNecessaryFetchMoreBeers(currentPage, beersSize)) page++ else page = -1
        } else {
            page = 1
        }
        return page
    }

    private fun isNecessaryFetchMoreBeers(page: Int, beersSize: Int): Boolean {
        return (beersSize / page) == MAX_RESULTS_PER_PAGE
    }

    override fun saveBeer(beer: BeerEntity): Boolean {
        val beerCache = EntityToLocalMapper.map(beer)
        return favoritesLocalDataSource.saveItem(beerCache)
    }

    override fun removeBeer(id: Int): Boolean {
        return favoritesLocalDataSource.removeItem(id)
    }

    override fun getFavouriteBeers(): BeersEntity {
        return LocalToEntityMapper.map(favoritesLocalDataSource.getItems())
    }
}