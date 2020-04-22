package com.rahulahuja.cheerswithbeer.data.datasource.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rahulahuja.cheerswithbeer.data.datasource.models.LocalBeerEntity
import java.io.File

/**
 * Created by rahulahuja on 21/04/20.
 */
class FavoritesLocalDataSource(
    private val file: File,
    private val gson: Gson) {

    fun saveItem(localBeerEntity: LocalBeerEntity?): Boolean {
        val listToSave = if (file.isFile) {
            val mutableItems: MutableList<LocalBeerEntity> = getItems().toMutableList()
            localBeerEntity?.let {
                mutableItems.add(it)
            }
            mutableItems
        } else {
            val firstItemWhenFileIsEmpty = listOf(localBeerEntity)
            firstItemWhenFileIsEmpty
        }

        file.writeText(gson.toJson(listToSave))

        return isObjectSaved(localBeerEntity)
    }

    fun getItems(): List<LocalBeerEntity> {
        val json = if (file.isFile) file.readText(Charsets.UTF_8) else ""

        return gson.fromJson(json, object : TypeToken<ArrayList<LocalBeerEntity?>?>() {}.type) ?: emptyList()
    }

    fun removeItem(itemId: Int): Boolean {
        if (file.isFile) {
            val mutableItems: MutableList<LocalBeerEntity> = getItems().toMutableList()
            val existItem = mutableItems.any { it.id == itemId }
            if (existItem) {
                val itemToRemove = mutableItems.filter { it.id == itemId }[0]
                mutableItems.remove(itemToRemove)
                file.writeText(gson.toJson(mutableItems.toList()))
            }
        }

        return isItemRemoved(itemId)
    }

    private fun isItemRemoved(itemId: Int): Boolean {
        return getItems().any { it.id == itemId }.not()
    }

    private fun isObjectSaved(beer: LocalBeerEntity?): Boolean {
        return getItems().any { beer?.id == it.id }
    }
}