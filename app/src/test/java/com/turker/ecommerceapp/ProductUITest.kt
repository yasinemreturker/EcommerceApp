package com.turker.ecommerceapp

import com.turker.ecommerceapp.data.model.ProductUI
import org.junit.Test

import org.junit.Assert.*

class ProductUITest {

    @Test
    fun `two ProductUI objects with same data are equal`() {
        val p1 = ProductUI(
            createdAt = "2023-01-01",
            name = "Test Product",
            image = "img.jpg",
            price = "100",
            description = "Desc",
            model = "M1",
            brand = "BrandA",
            id = "1",
            isFavorite = true
        )

        val p2 = ProductUI(
            createdAt = "2023-01-01",
            name = "Test Product",
            image = "img.jpg",
            price = "100",
            description = "Desc",
            model = "M1",
            brand = "BrandA",
            id = "1",
            isFavorite = true
        )

        assertEquals(p1, p2)
        assertEquals(p1.hashCode(), p2.hashCode())
        assertEquals(p1.toString(), p2.toString())
    }

    @Test
    fun `different ProductUI objects are not equal`() {
        val p1 = ProductUI("2023-01-01", "A", "img", "100", "desc", "M1", "B1", "1", true)
        val p2 = ProductUI("2023-01-02", "B", "img2", "200", "desc2", "M2", "B2", "2", false)
        assertNotEquals(p1, p2)
    }

    @Test
    fun `ProductUI handles null fields correctly`() {
        val p1 = ProductUI(null, null, null, null, null, null, null, null, false)
        val p2 = ProductUI(null, null, null, null, null, null, null, null, false)
        assertEquals(p1, p2)
        assertEquals(p1.hashCode(), p2.hashCode())
    }

    @Test
    fun `toString contains all non-null fields`() {
        val productUI = ProductUI(
            createdAt = "2023-01-01",
            name = "Name",
            image = null,
            price = "100",
            description = null,
            model = "M",
            brand = null,
            id = "1",
            isFavorite = true
        )
        val str = productUI.toString()
        assertTrue(str.contains("createdAt=2023-01-01"))
        assertTrue(str.contains("name=Name"))
        assertTrue(str.contains("image=null"))
        assertTrue(str.contains("price=100"))
        assertTrue(str.contains("description=null"))
        assertTrue(str.contains("model=M"))
        assertTrue(str.contains("brand=null"))
        assertTrue(str.contains("id=1"))
        assertTrue(str.contains("isFavorite=true"))
    }
}
