package com.meow.himmel.domain.model

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Failure(val exception: Exception) : Response<Nothing>()
    data object Waiting : Response<Nothing>()
}