package com.turker.ecommerceapp.data.model

import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class ProductUI(
    val createdAt: String?,
    val name: String?,
    val image: String?,
    val price: String?,
    val description: String?,
    val model: String?,
    val brand: String?,
    val id: String?,
    val isFavorite: Boolean
)