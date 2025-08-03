package com.turker.ecommerceapp.di

import com.turker.ecommerceapp.data.datasource.local.ProductDao
import com.turker.ecommerceapp.data.datasource.remote.ProductService
import com.turker.ecommerceapp.data.repo.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesProductRepository(
        productService: ProductService,
        productDao: ProductDao
    ): ProductRepository =
        ProductRepository(productService, productDao)


}