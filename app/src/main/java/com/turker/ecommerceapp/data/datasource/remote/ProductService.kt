package com.turker.ecommerceapp.data.datasource.remote

import com.turker.ecommerceapp.data.model.response.CRUDResponse
import com.turker.ecommerceapp.data.model.response.GetProductResponse
import com.turker.ecommerceapp.util.Constants.Endpoint.ADD_TO_CART
import com.turker.ecommerceapp.util.Constants.Endpoint.CLEAR_CART
import com.turker.ecommerceapp.util.Constants.Endpoint.DELETE_FROM_CART
import com.turker.ecommerceapp.util.Constants.Endpoint.GET_ALL_PRODUCTS
import com.turker.ecommerceapp.util.Constants.Endpoint.GET_CART_PRODUCTS
import com.turker.ecommerceapp.util.Constants.Endpoint.GET_PRODUCT_DETAIL
import com.turker.ecommerceapp.util.Constants.Endpoint.SEARCH_PRODUCT
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ProductService {

    @GET(GET_ALL_PRODUCTS)
    suspend fun getAllProducts(): GetProductResponse

//    @GET(GET_PRODUCT_DETAIL)
//    suspend fun getProductDetail(
//        @Query("id") id: Int
//    ): GetProductDetailResponse
//
//    @GET(SEARCH_PRODUCT)
//    suspend fun getSearchProduct(
//        @Query("query") query: String
//    ): GetSearchProductResponse
//
//    @POST(ADD_TO_CART)
//    suspend fun addToCart(
//        @Body addToCartRequest: AddToCartRequest
//    ): CRUDResponse
//
//    @GET(GET_CART_PRODUCTS)
//    suspend fun getCartProducts(
//        @Query("userId") userId: String
//    ): GetCartProductsResponse
//
//    @POST(DELETE_FROM_CART)
//    suspend fun deleteFromCart(
//        @Body deleteFromCartRequest: DeleteFromCartRequest
//    ): CRUDResponse
//
//    @POST(CLEAR_CART)
//    suspend fun clearCart(
//        @Body clearCartRequest: ClearCartRequest
//    ) : CRUDResponse

}