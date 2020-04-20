package com.rahulahuja.cheerswithbeer.business.domain.usecase

import com.rahulahuja.cheerswithbeer.business.`interface`.BeersRepository

/**
 * Created by rahulahuja on 20/04/20.
 */
class RemoveBeerUseCase(
    private val beersRepository: BeersRepository?) {
    fun execute(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}