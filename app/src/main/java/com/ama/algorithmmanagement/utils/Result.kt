package com.ama.algorithmmanagement.utils

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val isSuccess: Boolean) : Result<T>()
    data class Error<out T : Any>(val exeception: Exception) : Result<T>()
}
