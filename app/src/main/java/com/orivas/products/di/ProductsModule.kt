package com.orivas.products.di

import com.orivas.products.data.products.ProductsDataSource
import com.orivas.products.data.products.ProductsRemoteDataSource
import com.orivas.products.data.products.ProductsRepositoryImpl
import com.orivas.products.data.service.ProductsService
import com.orivas.products.domain.model.Products
import com.orivas.products.domain.repository.ProductsRepository
import com.orivas.products.domain.usecase.GetProductsUseCase
import com.orivas.products.domain.usecase.UseCase
import com.orivas.products.presentation.viewmodel.ProductsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ProductsModule {

    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }

    @Provides
    fun provideProductRemoteDataSource(retrofit: ProductsService): ProductsDataSource {
        return ProductsRemoteDataSource(retrofit)
    }

    @Provides
    fun provideProductsRepository(dataSource: ProductsDataSource): ProductsRepository {
        return ProductsRepositoryImpl(dataSource)
    }

    @Provides
    fun provideGetProductsUseCase(
        repository: ProductsRepository
    ): UseCase<GetProductsUseCase.Params, Products> {
        return GetProductsUseCase(repository)
    }

    @Provides
    fun provideProductsViewModel(
        getProductsUseCase: UseCase<GetProductsUseCase.Params, Products>
    ): ProductsViewModel {
        return ProductsViewModel(
            getProductsUseCase
        )
    }
}