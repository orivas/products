package com.orivas.products.domain.usecase

import com.orivas.products.domain.NetworkResult
import com.orivas.products.domain.model.Products
import com.orivas.products.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) : UseCase<GetProductsUseCase.Params, Products> {

    override suspend fun execute(params: Params?): Flow<NetworkResult<Products>> {
        return params?.let {
            productsRepository.getProducts(it.search, it.pageNumber)
        } ?: flowOf(NetworkResult.OnError())
    }

    data class Params(
        val search: String,
        val pageNumber: Int
    )

}