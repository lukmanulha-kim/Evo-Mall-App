package com.lukman.evomall.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val thumbnail: String
): Parcelable
