package com.turker.ecommerceapp.data.model

data class FilterResult(
    val brands: List<String>,
    val models: List<String>,
    val sort: String? = null
)