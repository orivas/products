package com.orivas.products.data.service

import com.orivas.products.data.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {

    @GET("plp")
    suspend fun getProducts(
        @Query("search-string")
        search: String,
        @Query("page-number")
        pageNumber: Int,
    ): Response<ProductsResponse>

}