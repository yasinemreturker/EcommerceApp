package com.turker.ecommerceapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.data.repo.ProductRepository
import com.turker.ecommerceapp.presentation.fragment.home.HomeState
import com.turker.ecommerceapp.presentation.fragment.home.HomeViewModel
import com.turker.ecommerceapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var observer: Observer<HomeState>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(productRepository)
        viewModel.homeState.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllProducts returns ProductList state on success`() = runTest {
        // Arrange
        val mockProductList = listOf(
            ProductUI("2023-08-01", "Product 1", "img.png", "100", "desc", "M1", "BrandX", "1", false),
            ProductUI("2023-08-02", "Product 2", "img2.png", "200", "desc2", "M2", "BrandY", "2", true)
        )
        `when`(productRepository.getAllProducts()).thenReturn(Resource.Success(mockProductList))

        // Act
        viewModel.getAllProducts()

        // Assert
        verify(observer).onChanged(HomeState.Loading)
        verify(observer).onChanged(HomeState.ProductList(mockProductList))
    }

    @Test
    fun `getAllProducts returns Error state on failure`() = runTest {
        // Arrange
        val exception = Exception("Error loading products")
        `when`(productRepository.getAllProducts()).thenReturn(Resource.Error(exception))

        // Act
        viewModel.getAllProducts()

        // Assert
        verify(observer).onChanged(HomeState.Loading)
        verify(observer).onChanged(HomeState.Error(exception))
    }
}

