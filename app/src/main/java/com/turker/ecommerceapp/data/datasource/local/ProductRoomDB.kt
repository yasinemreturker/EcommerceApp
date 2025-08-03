package com.turker.ecommerceapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.turker.ecommerceapp.data.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductRoomDB : RoomDatabase() {
    abstract fun productDao(): ProductDao
}