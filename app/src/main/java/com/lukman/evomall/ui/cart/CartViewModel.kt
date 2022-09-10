package com.lukman.evomall.ui.cart

import androidx.lifecycle.ViewModel
import com.lukman.evomall.data.local.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {
    val product = repository.getCart()

    fun deleteAllProduct(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllProduct()
        }
    }
}