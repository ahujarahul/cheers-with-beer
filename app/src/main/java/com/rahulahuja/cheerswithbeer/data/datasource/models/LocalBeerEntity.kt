package com.rahulahuja.cheerswithbeer.data.datasource.models

/**
 * Created by rahulahuja on 21/04/20.
 */
// BeerLocalModel
class LocalBeerEntity(
    var id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val abv: Double,
    var isFavorite: Boolean = false,
    val foodPairing: List<String>)