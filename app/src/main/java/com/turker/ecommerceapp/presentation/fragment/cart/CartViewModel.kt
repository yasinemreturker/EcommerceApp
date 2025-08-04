package com.turker.ecommerceapp.presentation.fragment.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turker.ecommerceapp.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var _cartState = MutableLiveData<CartState>()
    val cartState: LiveData<CartState>
        get() = _cartState

    private val _totalAmount = MutableLiveData(0.0)
    val totalAmount: LiveData<Double> = _totalAmount

    init {
        getCartProducts()
    }


    fun getCartProducts() {
        CoroutineScope(Dispatchers.IO).launch {
//            _cartState.value = CartState.Loading
//            val result = productRepository.getCartProducts(id)
//            if (result is Resource.Success) {
//                _cartState.value = CartState.CartList(result.data)
//                _totalAmount.value = result.data.sumOf { product ->
//                    product.price
//                }
//            } else if (result is Resource.Error) {
//                _cartState.value = CartState.Error(result.throwable)
//                clear()
//            }
        }
    }

    fun deleteFromCart(id: String?) {
        CoroutineScope(Dispatchers.IO).launch {
//            _cartState.value = CartState.Loading
//            val result = productRepository.deleteFromCart(id)
//            if (result is Resource.Success) {
//                _cartState.value = CartState.PostResponse(result.data)
//            } else if (result is Resource.Error) {
//                _cartState.value = CartState.Error(result.throwable)
//            }
        }
    }

    fun clearCart(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
//            _cartState.value = CartState.Loading
//            val result = productRepository.clearCart(id: Int)
//            if (result is Resource.Success) {
//                _cartState.value = CartState.PostResponse(result.data)
//                getCartProducts(id)
//            } else if (result is Resource.Error) {
//                _cartState.value = CartState.Error(result.throwable)
//            }
        }
    }

    fun increase(price: String?) {
        _totalAmount.postValue(price?.toDouble()?.let { _totalAmount.value?.plus(it) })
    }

    fun decrease(price: String?) {
        _totalAmount.postValue(price?.toDouble()?.let { _totalAmount.value?.minus(it) })
    }

    fun clear() {
        _totalAmount.postValue(0.0)
    }

}

