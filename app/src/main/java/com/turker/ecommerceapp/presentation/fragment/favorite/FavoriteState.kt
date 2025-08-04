package com.turker.ecommerceapp.presentation.fragment.favorite

import com.turker.ecommerceapp.data.model.ProductUI


sealed interface FavoriteState {
    object Loading : FavoriteState
    data class Data(val products: List<ProductUI>) : FavoriteState
    data class Error(val throwable: Throwable) : FavoriteState
}