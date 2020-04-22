package com.rahulahuja.cheerswithbeer.data.koinModules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rahulahuja.cheerswithbeer.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by rahulahuja on 22/04/20.
 */
val retrofitModule = module {
    single {
        getRetrofitInstance()
    }
}

private fun getRetrofitInstance(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}