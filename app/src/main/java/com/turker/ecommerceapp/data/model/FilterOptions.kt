package com.turker.ecommerceapp.data.model

data class FilterOptions(
    val selectedBrands: Set<String> = emptySet(),
    val selectedModels: Set<String> = emptySet(),
    val priceRange: ClosedFloatingPointRange<Float>? = null,
    val createdAfter: String? = null,
    val createdBefore: String? = null
)