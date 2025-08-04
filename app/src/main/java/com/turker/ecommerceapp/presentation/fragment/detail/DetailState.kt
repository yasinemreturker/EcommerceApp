package com.turker.ecommerceapp.presentation.fragment.detail

import com.turker.ecommerceapp.data.model.ProductUI

sealed interface DetailState {
    object Loading : DetailState
    data class Data(val product: ProductUI) : DetailState
    data class Error(val throwable: Throwable) : DetailState
}