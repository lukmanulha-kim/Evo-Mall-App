package com.lukman.evomall.data.local

import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    suspend fun addToCart(cartModel: CartModel) =
        cartDao.addToCart(cartModel)

    fun getCart() = cartDao.getCart()

    suspend fun checkProduct(id: String) = cartDao.checkProduct(id)

    suspend fun deleteProduct(id: String) {
        cartDao.deleteProduct(id)
    }

    suspend fun deleteAllProduct(){
        cartDao.deleteAllProduct()
    }
}