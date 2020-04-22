package com.rahulahuja.cheerswithbeer.business.domain.usecase

import com.rahulahuja.cheerswithbeer.business.interfaces.BeersRepository
import com.rahulahuja.cheerswithbeer.utils.NetworkResult
import com.rahulahuja.cheerswithbeer.enums.ResultType
import com.rahulahuja.cheerswithbeer.presentation.models.BeerEntity
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity

/**
 * Created by rahulahuja on 17/04/20.
 */
class GetBeersUseCase(
    private val beersRepository: BeersRepository?) {

    suspend fun execute(): NetworkResult<BeersEntity> {
        var beers = NetworkResult.success(BeersEntity(listOf()))

        beersRepository?.getAllBeers()?.let { beersEntity ->
            if (beersEntity.resultType == ResultType.SUCCESS) {
                beersEntity.data?.let {
                    val allBeersWithFavoritesChecked =
                        getAllBeersWithFavoritesChecked(it.beers.toMutableList()).toList()

                    val sortedBeers =
                        getSortedAscendingBeers(BeersEntity(allBeersWithFavoritesChecked))

                    beers = NetworkResult.success(sortedBeers)
                }
            } else {
                beers = NetworkResult.error(beersEntity.error)
            }
        }
        return beers
    }

    private fun getAllBeersWithFavoritesChecked(mutableAllBeers: MutableList<BeerEntity>): MutableList<BeerEntity> {
        val favoritesBeer = beersRepository?.getFavouriteBeers()?.beers
        favoritesBeer?.map { favoriteBeer ->
            val beerUncheckedAsFavorite =
                mutableAllBeers.filter { noFavoriteBeer ->
                    noFavoriteBeer.id == favoriteBeer.id
                }

            if (beerUncheckedAsFavorite.isNotEmpty()) {
                val beerToFavorite = beerUncheckedAsFavorite[0].apply { isFavorite = true }
                mutableAllBeers.remove(beerUncheckedAsFavorite[0])
                mutableAllBeers.add(beerToFavorite)
            }
        }
        return mutableAllBeers
    }

    private fun getSortedAscendingBeers(beersEntity: BeersEntity): BeersEntity {
        return BeersEntity(beersEntity.beers.sortedBy { beerEntity ->
            beerEntity.abv })
    }
}