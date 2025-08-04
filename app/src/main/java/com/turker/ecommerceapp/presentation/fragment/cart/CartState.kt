package com.turker.ecommerceapp.presentation.fragment.cart

import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.data.model.response.CRUDResponse

sealed interface CartState {
    object Loading : CartState
    data class PostResponse(val crud: CRUDResponse) : CartState
    data class CartList(val products: List<ProductUI>) : CartState
    data class Error(val throwable: Throwable) : CartState
}