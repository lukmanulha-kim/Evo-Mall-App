package com.lukman.evomall.ui.detail

import androidx.lifecycle.ViewModel
import com.lukman.evomall.data.local.CartModel
import com.lukman.evomall.data.local.CartRepository
import com.lukman.evomall.data.remote.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {
    fun addToCart(products: Products){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToCart(
                CartModel(
                    products.id,
                    products.title,
                    products.description,
                    products.price,
                    products.rating,
                    products.thumbnail
                )
            )
        }
    }

    suspend fun checkproduct(id: String) = repository.checkProduct(id)

    fun deleteProduct(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteProduct(id)
        }
    }
}