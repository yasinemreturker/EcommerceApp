package com.turker.ecommerceapp.presentation.fragment.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.databinding.ItemCartBinding

class CartAdapter(
    private val cartListener: CartListener
) : ListAdapter<ProductUI, CartAdapter.CartViewHolder>(CartDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(
            ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            cartListener,
        )

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CartViewHolder(
        private val binding: ItemCartBinding,
        private val cartListener: CartListener
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DefaultLocale")
        fun bind(product: ProductUI) = with(binding) {

            tvCartProductTitle.text = product.name
            tvCartProductPrice.text = product.price

            var productCount = 1

            buttonCountPlus.setOnClickListener {
                cartListener.onIncreaseItemClick(product.price)
                productCount++
                tvCartProductCount.text = productCount.toString()
            }

            buttonCountMinus.setOnClickListener {
                if (productCount != 1) {
                    cartListener.onDecreaseItemClick(product.price)
                } else {
                    cartListener.onDeleteItemClick(product.id)
                }

                productCount--
                tvCartProductCount.text = productCount.toString()
            }

            tvCartProductPrice.text = String.format("%.2f", product.price).plus(
                binding.root.context.getString(
                    R.string.turkish_lira_symbol
                )
            )

        }
    }

    class CartDiffCallBack : DiffUtil.ItemCallback<ProductUI>() {
        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface CartListener {
        fun onDeleteItemClick(id: String?)
        fun onIncreaseItemClick(price: String?)
        fun onDecreaseItemClick(price: String?)
    }
}