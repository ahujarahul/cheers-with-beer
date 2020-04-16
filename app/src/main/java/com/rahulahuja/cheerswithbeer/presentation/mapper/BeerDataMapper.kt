package com.rahulahuja.cheerswithbeer.presentation.mapper

import com.rahulahuja.cheerswithbeer.R
import com.rahulahuja.cheerswithbeer.presentation.enums.AbvColorType
import com.rahulahuja.cheerswithbeer.presentation.models.BeerAdapterModel
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