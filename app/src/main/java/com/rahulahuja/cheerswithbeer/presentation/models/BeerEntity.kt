package com.rahulahuja.cheerswithbeer.presentation.models

import com.rahulahuja.cheerswithbeer.business.enums.AbvRangeType

/**
 * Created by rahulahuja on 17/04/20.
 */
class BeerEntity(
    val id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val abv: Double,
    var isFavorite: Boolean = false,
    val foodPairing: List<String>
) {
    fun getAbvRange(abv: Double): AbvRangeType {
        return when {
            abv < 5 -> AbvRangeType.LOW
            abv >= 5 && abv < 8 -> AbvRangeType.NORMAL
            else -> AbvRangeType.HIGH
        }
    }
}