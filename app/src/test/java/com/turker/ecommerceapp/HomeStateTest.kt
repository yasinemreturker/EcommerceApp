package com.turker.ecommerceapp

import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.data.model.response.CRUDResponse
import com.turker.ecommerceapp.presentation.fragment.home.HomeState
import org.junit.Assert.*
import org.junit.Test

class HomeStateTest {

    @Test
    fun `Loading is singleton`() {
        val loading1 = HomeState.Loading
        val loading2 = HomeState.Loading

        assertSame(loading1, loading2)
    }

    @Test
    fun `ProductList holds correct product list`() {
        val products = listOf(
            ProductUI("2023-01-01", "Product1", "img1", "10.0", "desc", "Model1", "Brand1", "1", false),
            ProductUI("2023-01-02", "Product2", "img2", "20.0", "desc", "Model2", "Brand2", "2", true),
        )
        val productListState = HomeState.ProductList(products)

        assertEquals(2, productListState.products.size)
        assertEquals("Product1", productListState.products[0].name)
        assertEquals("Product2", productListState.products[1].name)
    }

    @Test
    fun `PostResponse holds correct crud response`() {
        val crudResponse = CRUDResponse(status = 200, message = "Ok")
        val postResponseState = HomeState.PostResponse(crudResponse)

        assertEquals(200, postResponseState.crud.status)
        assertEquals("Ok", postResponseState.crud.message)
    }

    @Test
    fun `Error holds throwable with correct message`() {
        val exception = Exception("Test error")
        val errorState = HomeState.Error(exception)

        assertEquals("Test error", errorState.throwable.message)
    }
}

