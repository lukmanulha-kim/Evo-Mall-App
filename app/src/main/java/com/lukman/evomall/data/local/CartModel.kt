package com.lukman.evomall.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "tbl_cart")
@Parcelize
data class CartModel(
    val id_product: String,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val thumbnail: String
): Serializable, Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
