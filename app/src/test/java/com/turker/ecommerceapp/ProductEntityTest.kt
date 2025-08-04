package com.turker.ecommerceapp

import com.turker.ecommerceapp.data.model.ProductEntity
import org.junit.Assert.*
import org.junit.Test

class ProductEntityTest {

    @Test
    fun `two ProductEntity objects with same data are equal`() {
        val p1 = ProductEntity(
            id = 1,
            category = "Electronics",
            count = 10,
            description = "Great product",
            imageOne = "img1.jpg",
            imageTwo = "img2.jpg",
            imageThree = "img3.jpg",
            price = 199.99,
            rate = 4.5,
            salePrice = 179.99,
            saleState = true,
            title = "Smartphone"
        )

        val p2 = ProductEntity(
            id = 1,
            category = "Electronics",
            count = 10,
            description = "Great product",
            imageOne = "img1.jpg",
            imageTwo = "img2.jpg",
            imageThree = "img3.jpg",
            price = 199.99,
            rate = 4.5,
            salePrice = 179.99,
            saleState = true,
            title = "Smartphone"
        )

        assertEquals(p1, p2)
        assertEquals(p1.hashCode(), p2.hashCode())
    }

    @Test
    fun `different ProductEntity objects are not equal`() {
        val p1 = ProductEntity(
            id = 1,
            category = "Electronics",
            count = 10,
            description = "Great product",
            imageOne = "img1.jpg",
            imageTwo = "img2.jpg",
            imageThree = "img3.jpg",
            price = 199.99,
            rate = 4.5,
            salePrice = 179.99,
            saleState = true,
            title = "Smartphone"
        )

        val p2 = ProductEntity(
            id = 2,
            category = "Home",
            count = 5,
            description = "Another product",
            imageOne = "imgA.jpg",
            imageTwo = "imgB.jpg",
            imageThree = "imgC.jpg",
            price = 99.99,
            rate = 3.9,
            salePrice = 89.99,
            saleState = false,
            title = "Vacuum Cleaner"
        )

        assertNotEquals(p1, p2)
    }

    @Test
    fun `ProductEntity handles null fields correctly`() {
        val p1 = ProductEntity(
            id = null,
            category = null,
            count = null,
            description = null,
            imageOne = null,
            imageTwo = null,
            imageThree = null,
            price = null,
            rate = null,
            salePrice = null,
            saleState = null,
            title = null
        )

        val p2 = ProductEntity(
            id = null,
            category = null,
            count = null,
            description = null,
            imageOne = null,
            imageTwo = null,
            imageThree = null,
            price = null,
            rate = null,
            salePrice = null,
            saleState = null,
            title = null
        )

        assertEquals(p1, p2)
        assertEquals(p1.hashCode(), p2.hashCode())
    }

    @Test
    fun `toString contains all field names and values`() {
        val product = ProductEntity(
            id = 5,
            category = "Books",
            count = 3,
            description = "Interesting book",
            imageOne = "img1.jpg",
            imageTwo = null,
            imageThree = "img3.jpg",
            price = 29.99,
            rate = 4.8,
            salePrice = 19.99,
            saleState = false,
            title = "Kotlin Guide"
        )

        val str = product.toString()
        assertTrue(str.contains("id=5"))
        assertTrue(str.contains("category=Books"))
        assertTrue(str.contains("count=3"))
        assertTrue(str.contains("description=Interesting book"))
        assertTrue(str.contains("imageOne=img1.jpg"))
        assertTrue(str.contains("imageTwo=null"))
        assertTrue(str.contains("imageThree=img3.jpg"))
        assertTrue(str.contains("price=29.99"))
        assertTrue(str.contains("rate=4.8"))
        assertTrue(str.contains("salePrice=19.99"))
        assertTrue(str.contains("saleState=false"))
        assertTrue(str.contains("title=Kotlin Guide"))
    }
}
