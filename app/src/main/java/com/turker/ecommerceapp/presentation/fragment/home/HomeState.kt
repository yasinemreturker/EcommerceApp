package com.turker.ecommerceapp.presentation.fragment.home

import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.data.model.response.CRUDResponse

sealed interface HomeState {
    object Loading : HomeState
    data class ProductList(val products: List<ProductUI>) : HomeState
    data class PostResponse(val crud: CRUDResponse) : HomeState
    data class Error(val throwable: Throwable) : HomeState
}