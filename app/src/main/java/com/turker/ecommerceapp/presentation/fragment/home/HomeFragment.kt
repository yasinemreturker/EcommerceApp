package com.turker.ecommerceapp.presentation.fragment.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.databinding.FragmentHomeBinding
import com.turker.ecommerceapp.presentation.adapter.ProductsAdapter
import com.turker.ecommerceapp.util.invisible
import com.turker.ecommerceapp.util.viewBinding
import com.turker.ecommerceapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductsAdapter.ProductListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val productsAdapter by lazy { ProductsAdapter(this) }
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProducts.adapter = productsAdapter
        viewModel.getAllProducts()
        //viewModel.getFavoriteProducts()
        observeData()
    }

    private fun observeData() {
        viewModel.homeState.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeState.Loading -> {
                    binding.progressBarHome.visible()
                }

                is HomeState.ProductList -> {
                    productsAdapter.submitList(state.products)
                    binding.progressBarHome.invisible()
                }

                is HomeState.PostResponse -> {
                    binding.progressBarHome.invisible()
                    Toast.makeText(
                        requireContext(),
                        state.crud.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is HomeState.Error -> {
                    binding.progressBarHome.invisible()
                    Toast.makeText(
                        requireContext(),
                        state.throwable.message.orEmpty(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onProductClick(id: String?) {
//        val direction = HomeFragmentDirections.homeToDetail(id)
//        findNavController().navigate(direction)
    }

    override fun onAddToCartButtonClick(id: String?) {
//        val addToCartRequest = AddToCartRequest(viewModelFirebase.currentUser!!.uid, id)
//        viewModel.addToCart(addToCartRequest)
    }

    override fun onFavoriteButtonClick(product: ProductUI) {
//        if (product.isFavorite) {
//            viewModel.removeFromFavorite(product)
//        } else {
//            viewModel.addToFavorite(product)
//        }
//
//        viewModel.getAllProducts()
//        viewModel.getSaleProducts()
    }

}