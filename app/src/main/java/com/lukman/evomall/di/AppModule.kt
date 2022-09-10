package com.lukman.evomall.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lukman.evomall.data.local.CartDao
import com.lukman.evomall.data.local.CartDatabase
import com.lukman.evomall.data.remote.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesProductApi(retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun providesCartDatabase(
        @ApplicationContext app : Context
    )= Room.databaseBuilder(
        app,
        CartDatabase::class.java,
        "evomall_db"
    ).build()

    @Provides
    @Singleton
    fun providesCartDao(db: CartDatabase) = db.getCartDao()
}