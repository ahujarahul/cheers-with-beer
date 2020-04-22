package com.rahulahuja.cheerswithbeer.data.datasource.api.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rahulahuja on 21/04/20.
 */
data class BeerApi(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("tagline") val tagline: String? = null,
    @SerializedName("image_url") val image: String? = null,
    @SerializedName("abv") val abv: Double? = null,
    @SerializedName("food_pairing") val foodPairing: List<String>? = null)