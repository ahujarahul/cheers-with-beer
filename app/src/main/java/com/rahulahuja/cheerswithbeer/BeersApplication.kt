package com.rahulahuja.cheerswithbeer

import android.app.Application
import com.rahulahuja.cheerswithbeer.data.koinModules.beersRepoModule
import com.rahulahuja.cheerswithbeer.data.koinModules.favoritesLocalDataSourceModule
import com.rahulahuja.cheerswithbeer.data.koinModules.retrofitModule
import com.rahulahuja.cheerswithbeer.koinModules.beersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by rahulahuja on 20/04/20.
 */
class BeersApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinForApp()
    }

    private fun initKoinForApp() {
        startKoin {
            androidLogger()
            androidContext(this@BeersApplication)
            modules(
                listOf(
                    beersModule,
                    retrofitModule,
                    beersRepoModule,
                    favoritesLocalDataSourceModule
                )
            )
        }
    }
}