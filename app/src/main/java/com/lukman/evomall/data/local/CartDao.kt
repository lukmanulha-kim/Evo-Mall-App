package com.lukman.evomall.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Insert
    suspend fun addToCart(cartModel: CartModel)

    @Query("SELECT count(tbl_cart.id_product) as jumlah_product, * FROM tbl_cart GROUP BY tbl_cart.id_product")
    fun getCart(): LiveData<List<CartModel>>

    @Query("SELECT count(*) FROM tbl_cart WHERE tbl_cart.id_product = :id")
    suspend fun checkProduct(id: String): Int

    @Query("DELETE FROM tbl_cart WHERE tbl_cart.id_product = :id")
    suspend fun deleteProduct(id: String):Int

    @Query("DELETE FROM tbl_cart")
    suspend fun deleteAllProduct()
}