package com.earl.setwallpaperapplication.data.remoteDataSource

import com.earl.setwallpaperapplication.domain.models.utils.ErrorResponse
import com.earl.setwallpaperapplication.domain.models.utils.RemoteResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

fun<T> apiRequestFlow(call: suspend () -> Response<T>): Flow<RemoteResponse<T>> = flow {
    emit(RemoteResponse.Loading)
    withTimeoutOrNull(20000L) {
        val response = call()
        try {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(RemoteResponse.Success(data))
                }
            } else {
                response.errorBody()?.let { error ->
                    error.close()
                    val parsedError: ErrorResponse = Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                    emit(RemoteResponse.Failure(parsedError.message, parsedError.code))
                }
            }
        } catch (e: Exception) {
            emit(RemoteResponse.Failure(e.message ?: e.toString(), 400))
        }
    } ?: emit(RemoteResponse.Failure("Timeout! Please try again.", 408))
}.flowOn(Dispatchers.IO)