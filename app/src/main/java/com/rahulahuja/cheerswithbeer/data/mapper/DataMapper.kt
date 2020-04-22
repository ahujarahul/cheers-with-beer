package com.rahulahuja.cheerswithbeer.data.mapper

import com.rahulahuja.cheerswithbeer.data.datasource.api.models.BeerApi
import com.rahulahuja.cheerswithbeer.data.datasource.models.LocalBeerEntity
import com.rahulahuja.cheerswithbeer.presentation.models.BeerEntity
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity
import com.rahulahuja.cheerswithbeer.utils.BaseMapper

/**
 * Created by rahulahuja on 21/04/20.
 */
object EntityToLocalMapper : BaseMapper<BeerEntity, LocalBeerEntity> {
    override fun map(data: BeerEntity?): LocalBeerEntity? {
        return data?.let {
            LocalBeerEntity(
                id = it.id,
                name = it.name,
                tagline = it.tagline,
                image = it.image,
                abv = it.abv,
                isFavorite = true,
                foodPairing = it.foodPairing
            )
        }
    }
}

object ApiToEntityMapper : BaseMapper<List<BeerApi>, BeersEntity> {
    override fun map(data: List<BeerApi>?): BeersEntity {
        return BeersEntity(
            data?.map {
                BeerEntity(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    tagline = it.tagline ?: "",
                    image = it.image ?: "",
                    abv = it.abv ?: -1.0,
                    isFavorite = false,
                    foodPairing = it.foodPairing ?: emptyList()
                )
            } ?: listOf()
        )
    }
}

object ApiToLocalModelMapper : BaseMapper<List<BeerApi>, List<LocalBeerEntity>> {
    override fun map(data: List<BeerApi>?): List<LocalBeerEntity> {
        return data?.map {
            LocalBeerEntity(
                id = it.id ?: -1,
                name = it.name ?: "",
                tagline = it.tagline ?: "",
                image = it.image ?: "",
                abv = it.abv ?: 0.0,
                isFavorite = false,
                foodPairing = it.foodPairing ?: emptyList()
            )
        } ?: emptyList()
    }
}

object LocalToEntityMapper : BaseMapper<List<LocalBeerEntity>, BeersEntity> {
    override fun map(data: List<LocalBeerEntity>?): BeersEntity {
        return BeersEntity(
            beers = data?.map {
                BeerEntity(
                    id = it.id,
                    name = it.name,
                    tagline = it.tagline,
                    image = it.image,
                    abv = it.abv,
                    isFavorite = it.isFavorite,
                    foodPairing = it.foodPairing
                )
            } ?: listOf()
        )
    }
}