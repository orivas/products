package com.orivas.products.data.products

import com.orivas.products.data.mappers.toDomain
import com.orivas.products.data.service.ProductsService
import com.orivas.products.domain.NetworkResult
import com.orivas.products.domain.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(
    private val productsService: ProductsService
) : ProductsDataSource {

    override suspend fun getProducts(
        search: String,
        pageNumber: Int
    ): Flow<NetworkResult<Products>> = flow {

        //remove only for test
        //delay(2000)
        with(productsService.getProducts(search, pageNumber)) {
            if (isSuccessful) {
                this.body()?.let {
                    emit(
                        NetworkResult.OnSuccess(
                            data = it.toDomain()
                        )
                    )
                }
            }
        }
    }.flowOn(Dispatchers.IO)
}