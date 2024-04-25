package com.meow.himmel.domain.model

sealed class Respond<out T> {
    data class Success<T>(val data: T) : Respond<T>()
    data class Error(val exception: Exception) : Respond<Nothing>()
    data object Waiting : Respond<Nothing>()
}