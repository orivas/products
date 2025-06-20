package com.orivas.products.domain.repository

import com.orivas.products.domain.NetworkResult
import com.orivas.products.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(
        search: String,
        pageNumber: Int
    ): Flow<NetworkResult<Products>>

}