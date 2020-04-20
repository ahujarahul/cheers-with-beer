package com.rahulahuja.cheerswithbeer.koinModules

import com.rahulahuja.cheerswithbeer.business.domain.usecase.GetBeersUseCase
import com.rahulahuja.cheerswithbeer.business.domain.usecase.RemoveBeerUseCase
import com.rahulahuja.cheerswithbeer.business.domain.usecase.SaveBeerUseCase
import com.rahulahuja.cheerswithbeer.presentation.viewModel.beers.BeersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by rahulahuja on 20/04/20.
 */

val beersModule = module {

    // declare viewModels
    viewModel {
        BeersViewModel (get(), get(), get())
    }

    // declare use cases
    // classes defined with 'factory' indicate a new instance will be created each time
    factory { GetBeersUseCase(get()) }
    factory { SaveBeerUseCase(get()) }
    factory { RemoveBeerUseCase(get()) }
}