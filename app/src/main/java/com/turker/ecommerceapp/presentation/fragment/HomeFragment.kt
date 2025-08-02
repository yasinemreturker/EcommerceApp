package com.turker.ecommerceapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.databinding.FragmentHomeBinding
import com.turker.ecommerceapp.presentation.adapter.ProductsAdapter

class HomeFragment : Fragment(), ProductsAdapter.ProductListener {

    private lateinit var binding: FragmentHomeBinding
    private val productsAdapter by lazy { ProductsAdapter(this) }
    //private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProducts.adapter = productsAdapter

        //viewModel.getAllProducts()
        //viewModel.getFavoriteProducts()
        observeData()
    }

    private fun observeData() {
//        viewModel.homeState.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                HomeState.Loading -> {
//                    binding.progressBarHome.visible()
//                }
//
//                is HomeState.ProductList -> {
//                    productsAdapter.submitList(state.products)
//                    binding.progressBarHome.invisible()
//                }
//
//                is HomeState.SaleProductList -> {
//                    discountsAdapter.submitList(state.saleProducts)
//                    binding.progressBarHome.invisible()
//                }
//
//                is HomeState.PostResponse -> {
//                    binding.progressBarHome.invisible()
//                    Toast.makeText(
//                        requireContext(),
//                        state.crud.message,
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                is HomeState.Error -> {
//                    binding.progressBarHome.invisible()
//                    Toast.makeText(
//                        requireContext(),
//                        state.throwable.message.orEmpty(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
    }

    override fun onProductClick(id: Int) {
//        val direction = HomeFragmentDirections.homeToDetail(id)
//        findNavController().navigate(direction)
    }

    override fun onAddToCartButtonClick(id: Int) {
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