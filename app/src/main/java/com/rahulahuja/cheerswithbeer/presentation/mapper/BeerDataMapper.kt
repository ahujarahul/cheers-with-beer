package com.rahulahuja.cheerswithbeer.presentation.mapper

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.enums.AbvColorType
import com.rahulahuja.cheerswithbeer.presentation.enums.AbvRangeType
import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel
import com.rahulahuja.cheerswithbeer.presentation.models.BeerEntity
import com.rahulahuja.cheerswithbeer.presentation.models.BeerUI

/**
 * Created by rahulahuja on 16/04/20.
 */

object BeerAdapterModelToBeerUiMapper : BaseMapper<BeerAdapterModel, BeerUI> {
    override fun map(data: BeerAdapterModel?): BeerUI? {
        data?.let { beerAdapterModel ->
            return BeerUI(
                id = beerAdapterModel.id,
                name = beerAdapterModel.name,
                tagline = beerAdapterModel.tagline,
                image = beerAdapterModel.image,
                abv = beerAdapterModel.abv,
                abvColorType = getColorType(beerAdapterModel.abvColor),
                isFavorite = beerAdapterModel.isFavorite,
                foodPairing = beerAdapterModel.foodPairing
            )
        } ?: return null
    }
}

object BeerUIToAdapterModelMapper : BaseMapper<List<BeerUI>, List<BeerAdapterModel>> {
    override fun map(data: List<BeerUI>?): List<BeerAdapterModel> {
        return data?.map { beerUi ->
            BeerAdapterModel(
                id = beerUi.id,
                name = beerUi.name,
                tagline = beerUi.tagline,
                image = beerUi.image,
                abv = beerUi.abv,
                abvColor = getColor(beerUi.abvColorType),
                isFavorite = beerUi.isFavorite,
                foodPairing = beerUi.foodPairing
            )
        } ?: listOf()
    }
}

object BeersEntityToUIMapper : BaseMapper<List<BeerEntity>, List<BeerUI>> {
    override fun map(type: List<BeerEntity>?): List<BeerUI> {
        return type?.map {
            BeerUI(
                id = it.id,
                name = it.name,
                tagline = it.tagline,
                image = it.image,
                abv = it.abv,
                abvColorType = mapAbvType(it.getAbvRange(it.abv)),
                isFavorite = it.isFavorite,
                foodPairing = it.foodPairing.map { foodPairing ->
                    "- $foodPairing"
                }
            )
        } ?: listOf()
    }
}

private fun mapAbvType(abvRangeType: AbvRangeType): AbvColorType {
    return when (abvRangeType) {
        AbvRangeType.LOW -> AbvColorType.GREEN
        AbvRangeType.NORMAL -> AbvColorType.ORANGE
        AbvRangeType.HIGH -> AbvColorType.RED
    }
}

private fun getColor(abvType: AbvColorType): Int {
    return when (abvType) {
        AbvColorType.GREEN -> R.color.beerGradGreen
        AbvColorType.ORANGE -> R.color.beerGradYellow
        else -> R.color.beerGradRed
    }
}

private fun getColorType(abvColor: Int): AbvColorType {
    return when (abvColor) {
        R.color.beerGradGreen -> AbvColorType.GREEN
        R.color.beerGradYellow -> AbvColorType.ORANGE
        else -> AbvColorType.RED
    }
}