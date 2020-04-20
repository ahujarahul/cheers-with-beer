package com.rahulahuja.cheerswithbeer.business.domain.usecase

import com.rahulahuja.cheerswithbeer.business.`interface`.BeersRepository
import com.rahulahuja.cheerswithbeer.presentation.models.BeerEntity

/**
 * Created by rahulahuja on 20/04/20.
 */
class SaveBeerUseCase(
    private val beersRepository: BeersRepository?) {

    fun execute(beerEntity: BeerEntity): Boolean {
        // TODO: call repo function to save beer here
        return false
    }
}