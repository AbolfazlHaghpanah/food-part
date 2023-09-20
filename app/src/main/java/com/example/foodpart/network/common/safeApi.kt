package com.example.foodpart.network.common

import com.example.foodpart.core.Result
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import retrofit2.Response

suspend fun <T> safeApi(
    call: suspend () -> Response<T>,
    onDataReady: (T) -> Unit

): Flow<Result> {
    return flow {
        emit(Result.Loading)
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    if (currentCoroutineContext().isActive) {
                        onDataReady(body)
                    }
                    emit(Result.Success)
                } else {
                    emit(Result.Error("body_was_empty"))
                }
            } else {
                emit(Result.Error("not_success_response"))
            }
        } catch (t: Throwable) {
            emit(Result.Error("no_Status"))
        }

    }.cancellable()
}