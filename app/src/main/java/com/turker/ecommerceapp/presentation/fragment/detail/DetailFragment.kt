package com.turker.ecommerceapp.presentation.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.databinding.FragmentDetailBinding
import com.turker.ecommerceapp.presentation.fragment.home.HomeState
import com.turker.ecommerceapp.presentation.fragment.home.HomeViewModel
import com.turker.ecommerceapp.util.invisible
import com.turker.ecommerceapp.util.loadImage
import com.turker.ecommerceapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()
    private val viewModelHome by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        buttonController()
        //viewModel.getProductDetail(args.id)  // Ek olarak remote veya local içinde viewModel yolu izlenebilir.
        observeData()
    }

    private fun buttonController() {
        binding.btnAddToCart.setOnClickListener {
            viewModelHome.homeState.observe(viewLifecycleOwner) { state ->
                if (state is HomeState.PostResponse) {
                    Toast.makeText(
                        requireContext(),
                        state.crud.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initView() {
        with(binding) {
            ivProduct.loadImage(args.image)
            tvProductTitle.text = args.name
            tvProductDesc.text = args.description
            tvProductPrice.text = "${args.price} ₺"
        }
    }

    private fun observeData() = with(binding) {
        viewModel.detailState.observe(viewLifecycleOwner) { state ->

            when (state) {
                DetailState.Loading -> {
                    progressBarDetail.visible()
                }

                is DetailState.Data -> {
                    progressBarDetail.invisible()
                    with(state) {
                        ivProduct.loadImage(product.image)
                        tvProductTitle.text = product.name
                        tvProductDesc.text = product.description
                        tvProductPrice.text = "₺${product.price}"
                    }
                }

                is DetailState.Error -> {
                    progressBarDetail.invisible()
                    Toast.makeText(
                        requireContext(),
                        state.throwable.message.orEmpty(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }


}