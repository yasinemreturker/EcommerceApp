package com.turker.ecommerceapp.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turker.ecommerceapp.data.repo.ProductRepository
import com.turker.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var _homeState = MutableLiveData<HomeState>()
    val homeState: LiveData<HomeState>
        get() = _homeState

    fun getAllProducts() {
        CoroutineScope(Dispatchers.IO).launch {

            _homeState.postValue(HomeState.Loading)

            val result = productRepository.getAllProducts()

            if (result is Resource.Success) {
                _homeState.postValue(HomeState.ProductList(result.data))
            } else if (result is Resource.Error) {
                _homeState.postValue(HomeState.Error(result.throwable))
            }

        }
    }

    fun getFavoriteProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.getFavoriteProducts()
        }
    }

}