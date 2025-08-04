package com.turker.ecommerceapp.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.data.model.ProductUI
import com.turker.ecommerceapp.databinding.ProductItemBinding
import com.turker.ecommerceapp.util.loadImage

class ProductsAdapter(
    private val productListener: ProductListener
) : ListAdapter<ProductUI, ProductsAdapter.ProductViewHolder>(ProductDiffCallBack()) {

    private var matchedProduct: List<ProductUI> = arrayListOf()
    private var isSearchOnPressed: Boolean = false
    private var cartList: MutableList<ProductUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            productListener, matchedProduct, cartList
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (isSearchOnPressed && !matchedProduct.isNullOrEmpty()) {
            holder.bind(matchedProduct.get(position))
        } else {
            holder.bind(getItem(position))
        }
    }

    fun submitSearchList(matchedProduct: List<ProductUI>) {
        this.matchedProduct = matchedProduct
        isSearchOnPressed = true
    }

    class ProductViewHolder(
        private val binding: ProductItemBinding,
        private val productListener: ProductListener,
        private val matchedProduct: List<ProductUI>,
        private val cartList: MutableList<ProductUI>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductUI) = with(binding) {
            tvProductPrice.text =
                product.price.plus(binding.root.context.getString(R.string.turkish_lira_symbol))
            tvProductTitle.text = product.name
            ivProduct.loadImage(product.image)

            var isFavorite = product.isFavorite

            if (isFavorite) {
                ivFavorite.setImageResource(R.drawable.ic_favorite_enable)
            }

            cvProductItem.setOnClickListener {
                productListener.onProductClick(
                    product.id,
                    product.image,
                    product.name,
                    product.description,
                    product.price
                )
            }

            buttonAddToCart.setOnClickListener {
                cartList.add(product)
                productListener.onAddToCartButtonClick(cartList)
            }

            ivFavorite.setOnClickListener {
                isFavorite = !isFavorite
                ivFavorite.apply {
                    if (isFavorite) {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_enable)
                    } else {
                        ivFavorite.setImageResource(R.drawable.ic_favorite_disable)
                    }
                }
                productListener.onFavoriteButtonClick(product)
            }
        }
    }

    class ProductDiffCallBack : DiffUtil.ItemCallback<ProductUI>() {
        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface ProductListener {
        fun onProductClick(
            id: String?,
            image: String?,
            name: String?,
            description: String?,
            price: String?
        )

        fun onAddToCartButtonClick(cartList: MutableList<ProductUI>)
        fun onFavoriteButtonClick(product: ProductUI)
    }

}