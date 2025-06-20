package com.orivas.products.data.products

import com.orivas.products.domain.NetworkResult
import com.orivas.products.domain.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    suspend fun getProducts(
        search: String,
        pageNumber: Int
    ): Flow<NetworkResult<Products>>

}