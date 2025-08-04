package com.turker.ecommerceapp.presentation.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.data.repo.ProductRepository
import com.turker.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var _favoriteState = MutableLiveData<FavoriteState>()
    val favoriteState: LiveData<FavoriteState>
        get() = _favoriteState


    fun getFavoriteProducts() {
        CoroutineScope(Dispatchers.IO).launch {

            _favoriteState.postValue(FavoriteState.Loading)

            val result = productRepository.getFavoriteProducts()
            if (result is Resource.Success) {
                _favoriteState.postValue(FavoriteState.Data(result.data))
            } else if (result is Resource.Error) {
                _favoriteState.postValue(FavoriteState.Error(result.throwable))
            }
        }
    }

    fun removeFromFavorites(product: ProductUI) {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.removeFromFavorites(product)
            getFavoriteProducts()
        }
    }
}
