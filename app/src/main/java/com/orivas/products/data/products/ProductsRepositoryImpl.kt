package com.orivas.products.data.products

import com.orivas.products.domain.NetworkResult
import com.orivas.products.domain.model.Products
import com.orivas.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject  constructor(
    private val dataSource: ProductsDataSource
): ProductsRepository {

    override suspend fun getProducts(
        search: String,
        pageNumber: Int
    ): Flow<NetworkResult<Products>> {
        return dataSource.getProducts(search, pageNumber)
    }
}