package com.turker.ecommerceapp.data.mapper

import com.turker.ecommerceapp.data.model.Product
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.util.Constants.StringConstant.EMPTY_STRING

fun Product.mapToProductUI(isFavorite: Boolean): ProductUI {
    return ProductUI(
        createdAt = this.createdAt ?: EMPTY_STRING,
        image = this.image ?: EMPTY_STRING,
        description = this.description ?: EMPTY_STRING,
        model = this.model ?: EMPTY_STRING,
        brand = this.brand ?: EMPTY_STRING,
        name = this.name ?: EMPTY_STRING,
        id = this.id ?: EMPTY_STRING,
        price = this.price ?: EMPTY_STRING,
        isFavorite = isFavorite
    )
}
