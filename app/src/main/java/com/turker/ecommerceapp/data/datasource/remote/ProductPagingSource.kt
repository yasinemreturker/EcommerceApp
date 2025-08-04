package com.turker.ecommerceapp.data.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.turker.ecommerceapp.data.model.FilterOptions
import com.turker.ecommerceapp.data.model.Product

class ProductPagingSource(
    private val service: ProductService,
    private val filters: FilterOptions
) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val response = service.getAllProducts()

            val filtered = response.filter { product ->
                (filters.selectedBrands.isEmpty() || filters.selectedBrands.contains(product.brand)) &&
                        (filters.selectedModels.isEmpty() || filters.selectedModels.contains(product.model)) &&
                        (filters.priceRange == null || (product.price?.toFloatOrNull() ?: 0f) in filters.priceRange) &&
                        (filters.createdAfter == null || (product.createdAt ?: "") >= filters.createdAfter) &&
                        (filters.createdBefore == null || (product.createdAt ?: "") <= filters.createdBefore)
            }

            LoadResult.Page(
                data = filtered,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (filtered.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = 1
}
