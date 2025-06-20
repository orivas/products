package com.orivas.products.domain

sealed class NetworkResult<out T : Any?> {
    class OnSuccess<out T : Any?>(val data: T) : NetworkResult<T>()
    class OnError(val exception: Throwable = Exception()) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}