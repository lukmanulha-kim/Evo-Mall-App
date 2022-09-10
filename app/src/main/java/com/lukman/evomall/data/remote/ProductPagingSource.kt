package com.lukman.evomall.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE_INDEX = 0

class ProductPagingSource(
    private val productApi: ProductApi
): PagingSource<Int, Products>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Products> {
        return try {
            val position = params.key ?: START_PAGE_INDEX
            val response = productApi.getProducts(position)
            val products = response.products

            LoadResult.Page(
                data = products,
                prevKey = if(position == START_PAGE_INDEX) null else position-30,
                nextKey = if (products.isEmpty()) null else position+30
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Products>): Int? {
        return state.anchorPosition
    }
}