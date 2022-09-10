package com.lukman.evomall.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CartModel::class],
    version = 1
)
abstract class CartDatabase: RoomDatabase(){
    abstract fun getCartDao(): CartDao
}