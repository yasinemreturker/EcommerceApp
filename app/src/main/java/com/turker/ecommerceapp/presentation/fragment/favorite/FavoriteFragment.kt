package com.turker.ecommerceapp.presentation.fragment.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel by viewModels<FavoriteViewModel>()

}