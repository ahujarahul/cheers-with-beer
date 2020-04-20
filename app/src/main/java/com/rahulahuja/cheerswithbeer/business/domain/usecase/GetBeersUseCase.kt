package com.rahulahuja.cheerswithbeer.business.domain.usecase

import com.rahulahuja.cheerswithbeer.business.`interface`.BeersRepository
import com.rahulahuja.cheerswithbeer.data.NetworkResult
import com.rahulahuja.cheerswithbeer.presentation.models.BeersEntity

/**
 * Created by rahulahuja on 17/04/20.
 */
class GetBeersUseCase(
    private val beersRepository: BeersRepository?) {

    fun execute(): NetworkResult<BeersEntity> {
        TODO("Not yet implemented")
    }
}