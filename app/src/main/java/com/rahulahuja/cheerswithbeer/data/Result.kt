package com.rahulahuja.cheerswithbeer.data

import com.rahulahuja.cheerswithbeer.presentation.enums.ResultType

/**
 * Created by rahulahuja on 17/04/20.
 */
data class Result<out T>(
    var resultType: ResultType,
    val data: T? = null,
    val error: Exception? = null
) {

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(ResultType.SUCCESS, data)
        }

        fun <T> error(error: java.lang.Exception? = null): Result<T> {
            return Result(ResultType.ERROR, error = error)
        }
    }
}