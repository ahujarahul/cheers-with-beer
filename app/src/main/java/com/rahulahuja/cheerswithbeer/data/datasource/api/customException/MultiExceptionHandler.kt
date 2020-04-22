package com.rahulahuja.cheerswithbeer.data.datasource.api.customException

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * Created by rahulahuja on 22/04/20.
 */
fun handleNetworkExceptions(
    exception: Exception): Exception {

    return when (exception) {
        is IOException, is UnknownHostException -> NetworkConnectionException()
        is HttpException -> apiErrorException(exception.code())
        else -> GenericNetworkException()
    }
}

private fun apiErrorException(httpErrorCode: Int): Exception {
    return if (httpErrorCode == 400) {
        BadRequestException()
    } else {
        GenericNetworkException()
    }
}