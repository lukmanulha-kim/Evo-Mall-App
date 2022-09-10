package com.lukman.evomall.ui.product

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lukman.evomall.data.remote.ProductRepository
import com.lukman.evomall.data.remote.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository): ViewModel() {
    fun fetchProduct() : LiveData<PagingData<Products>>{
        val products = repository.getProducts()

        return products.cachedIn(viewModelScope)
    }
}