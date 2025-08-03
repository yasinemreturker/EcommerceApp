package com.turker.ecommerceapp.data.model.response

import com.turker.ecommerceapp.data.model.Product

data class GetProductResponse(
    val message: String?,
    val products: List<Product>?,
    val status: Int?
)