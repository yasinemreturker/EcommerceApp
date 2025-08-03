package com.turker.ecommerceapp.util

object Constants {
    const val BASE_URL = "https://5fc9346b2af77700165ae514.mockapi.io/"

    object Endpoint {
        const val GET_ALL_PRODUCTS = "products"
        const val GET_PRODUCT_DETAIL = "get_product_detail"
        const val GET_CART_PRODUCTS = "get_cart_products"
        const val ADD_TO_CART = "add_to_cart"
        const val DELETE_FROM_CART = "delete_from_cart"
        const val CLEAR_CART = "clear_cart"
        const val SEARCH_PRODUCT = "search_product"
    }

    object StringConstant {
        const val EMPTY_STRING = ""
    }

    object NumberConstant {
        const val ZERO = 0
        const val ONE = 1
        const val DOUBLE_ZERO = 0.0
    }
}