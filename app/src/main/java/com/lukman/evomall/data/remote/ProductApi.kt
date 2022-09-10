package com.lukman.evomall.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    companion object{
        const val BASE_URL = "https://dummyjson.com/"
    }

    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int
    ): ProductResponse
}