package com.orivas.products.domain.usecase

import com.orivas.products.domain.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, T> {
    suspend fun execute(params: Params? = null): Flow<NetworkResult<T>>
}