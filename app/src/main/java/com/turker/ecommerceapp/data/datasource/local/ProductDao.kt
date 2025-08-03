package com.turker.ecommerceapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.turker.ecommerceapp.data.model.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM favproducts")
    fun getFavoriteProducts(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorites(product: ProductEntity)

    @Delete
    fun removeFromFavorites(product: ProductEntity)

    @Query("SELECT id FROM favproducts")
    fun getFavoriteIds(): List<Int>

}