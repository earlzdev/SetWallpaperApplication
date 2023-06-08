package com.earl.setwallpaperapplication.domain.models.utils

sealed class RemoteResponse<out T> {

    object Loading: RemoteResponse<Nothing>()

    data class Success<out T>(
        val data: T
    ): RemoteResponse<T>()

    data class Failure(
        val errorMessage: String,
        val code: Int,
    ): RemoteResponse<Nothing>()
}