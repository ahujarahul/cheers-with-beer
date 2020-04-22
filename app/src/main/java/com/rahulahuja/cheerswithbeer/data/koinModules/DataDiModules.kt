package com.rahulahuja.cheerswithbeer.data.koinModules

import android.content.Context
import com.google.gson.GsonBuilder
import com.rahulahuja.cheerswithbeer.BuildConfig
import com.rahulahuja.cheerswithbeer.data.datasource.api.BeersNetworkDataSource
import com.rahulahuja.cheerswithbeer.data.datasource.api.network.BeersApiService
import com.rahulahuja.cheerswithbeer.data.datasource.cache.BeersCacheDataSource
import com.rahulahuja.cheerswithbeer.data.datasource.local.FavoritesLocalDataSource
import com.rahulahuja.cheerswithbeer.data.repo.BeersRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import java.io.File

/**
 * Created by rahulahuja on 21/04/20.
 */

val beersRepoModule = module {

    factory { createBeersApiService(get()) }
    factory { BeersNetworkDataSource(get()) }
    factory { BeersCacheDataSource() }

    single {
        BeersRepositoryImpl(get(), get(), get())
    }
}

private fun createBeersApiService(retrofit: Retrofit):
        BeersApiService = retrofit.create(BeersApiService::class.java)

val favoritesLocalDataSourceModule = module {
    factory {
        FavoritesLocalDataSource(
            getFavoritesBeersFile(get()),
            getGson()
        )
    }
}

private fun getGson() = GsonBuilder().setPrettyPrinting().create()

private fun getFavoritesBeersFile(context: Context): File {
    val filePath: String = context.filesDir.path.toString() + "/${BuildConfig.FILE_FAVORITES_BEERS}"
    return File(filePath)
}