package com.turker.ecommerceapp.presentation.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.turker.ecommerceapp.data.datasource.remote.ProductPagingSource
import com.turker.ecommerceapp.data.datasource.remote.ProductService
import com.turker.ecommerceapp.data.model.FilterOptions
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.data.repo.ProductRepository
import com.turker.ecommerceapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository, private val service: ProductService) :
    ViewModel() {
    private val _filterState = MutableStateFlow(FilterOptions())
    val filterState: StateFlow<FilterOptions> = _filterState.asStateFlow()


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

    val products = _filterState.flatMapLatest { filters ->
        Pager(PagingConfig(pageSize = 4)) {
            ProductPagingSource(service, filters)
        }.flow.cachedIn(viewModelScope)
    }

    fun setFilters(filters: FilterOptions) {
        _filterState.value = filters
    }

    fun getFavoriteProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.getFavoriteProducts()
        }
    }

    fun removeFromFavorite(product: ProductUI) {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.removeFromFavorites(product)
        }
    }

    fun addToFavorite(product: ProductUI) {
        CoroutineScope(Dispatchers.IO).launch {
            productRepository.addToFavorites(product)
        }
    }

}