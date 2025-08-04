package com.turker.ecommerceapp

import com.turker.ecommerceapp.data.model.Product
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ProductTest {
    @Test
    fun `product equality based on all fields`() {
        val product1 = Product("1", "Phone", "img.jpg", "1000", "Nice", "X1", "BrandA", "2023-01-01")
        val product2 = Product("1", "Phone", "img.jpg", "1000", "Nice", "X1", "BrandA", "2023-01-01")
        assertEquals(product1, product2)
    }

    @Test
    fun `product hashCode should be equal for same values`() {
        val product1 = Product("1", "Phone", "img.jpg", "1000", "Nice", "X1", "BrandA", "2023-01-01")
        val product2 = Product("1", "Phone", "img.jpg", "1000", "Nice", "X1", "BrandA", "2023-01-01")
        assertEquals(product1.hashCode(), product2.hashCode())
    }
}