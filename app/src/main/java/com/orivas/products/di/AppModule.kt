package com.orivas.products.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE = "https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder().apply {
                    readTimeout(15, TimeUnit.SECONDS)
                    connectTimeout(15, TimeUnit.SECONDS)
                }.build()
            ).build()
    }

}