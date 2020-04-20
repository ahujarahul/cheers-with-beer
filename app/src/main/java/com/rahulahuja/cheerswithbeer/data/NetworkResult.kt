package com.rahulahuja.cheerswithbeer.data

import com.rahulahuja.cheerswithbeer.data.enums.ResultType

/**
 * Created by rahulahuja on 17/04/20.
 */
data class NetworkResult<out T>(
    var resultType: ResultType,
    val data: T? = null,
    val error: Exception? = null
) {

    companion object {
        fun <T> success(data: T?): NetworkResult<T> {
            return NetworkResult(ResultType.SUCCESS, data)
        }

        fun <T> error(error: java.lang.Exception? = null): NetworkResult<T> {
            return NetworkResult(ResultType.ERROR, error = error)
        }
    }
}