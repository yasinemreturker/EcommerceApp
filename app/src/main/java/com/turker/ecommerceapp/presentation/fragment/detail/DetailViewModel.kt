package com.turker.ecommerceapp.presentation.fragment.detail

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
class DetailViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private var _detailState = MutableLiveData<DetailState>()
    val detailState: LiveData<DetailState>
        get() = _detailState

    // Api den bu datayı alsaydık bu metodu kullanabilirdik
//    fun getProductDetail(id: Int) {
//        CoroutineScope(Dispatchers.IO).launch {
//            _detailState.value = DetailState.Loading
//            val result = productRepository.getProductDetail(id)
//            if (result is Resource.Success) {
//                _detailState.value = DetailState.Data(result.data)
//            } else if (result is Resource.Error) {
//                _detailState.value = DetailState.Error(result.throwable)
//            }
//        }
//    }
}
