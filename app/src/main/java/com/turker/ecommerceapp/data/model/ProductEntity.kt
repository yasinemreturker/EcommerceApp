package com.turker.ecommerceapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favproducts")
data class ProductEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "createdAt")
    val createdAt: String?,

    @ColumnInfo(name = "count")
    val name: String?,

    @ColumnInfo(name = "description")
    val image: String?,

    @ColumnInfo(name = "imageOne")
    val price: String?,

    @ColumnInfo(name = "imageThree")
    val description: String?,

    @ColumnInfo(name = "imageTwo")
    val model: String?,

    @ColumnInfo(name = "price")
    val brand: String?
)
