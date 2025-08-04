package com.turker.ecommerceapp

import com.turker.ecommerceapp.data.datasource.local.ProductDao
import com.turker.ecommerceapp.data.datasource.remote.ProductService
import com.turker.ecommerceapp.data.model.Product
import com.turker.ecommerceapp.data.model.ProductEntity
import com.turker.ecommerceapp.data.repo.ProductRepository
import com.turker.ecommerceapp.util.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {

    private lateinit var repository: ProductRepository

    // Fake servis
    private val fakeService = object : ProductService {
        override suspend fun getAllProducts(): List<Product> = listOf(
            Product("1", "Phone", "img.jpg", "100", "desc", "M1", "BrandA", "2023-01-01"),
            Product("2", "Tablet", "img2.jpg", "200", "desc", "M2", "BrandB", "2023-02-01")
        )
    }

    // Fake DAO, favori id'leri döndürüyor
    private val fakeDao = object : ProductDao {

        override fun getFavoriteProducts(): List<ProductEntity> {
            TODO("Not yet implemented")
        }

        override fun addToFavorites(product: ProductEntity) {
            TODO("Not yet implemented")
        }

        override fun removeFromFavorites(product: ProductEntity) {
            TODO("Not yet implemented")
        }

        override fun getFavoriteIds(): List<Int> {
            return listOf(1, 2, 3) // Test için sabit favori id listesi
        }

    }

    @Before
    fun setup() {
        repository = ProductRepository(fakeService, fakeDao)
    }

    @Test
    fun `getAllProducts returns error if empty list`() = runTest {
        val emptyService = object : ProductService {
            override suspend fun getAllProducts(): List<Product> = emptyList()
        }
        val repoEmpty = ProductRepository(emptyService, fakeDao)

        val result = repoEmpty.getAllProducts()
        assertTrue(result is Resource.Error)
        assertEquals("Products not found", (result as Resource.Error).throwable.message)
    }

    @Test
    fun `getAllProducts returns error on exception`() = runTest {
        val errorService = object : ProductService {
            override suspend fun getAllProducts(): List<Product> {
                throw RuntimeException("Network error")
            }
        }
        val repoError = ProductRepository(errorService, fakeDao)

        val result = repoError.getAllProducts()
        assertTrue(result is Resource.Error)
        assertEquals("Network error", (result as Resource.Error).throwable.message)
    }
}

