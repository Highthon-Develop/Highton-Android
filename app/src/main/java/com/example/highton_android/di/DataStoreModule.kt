package com.example.highton_android.di

import android.content.Context
import com.example.highton_android.data.TokenDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun providesTokenDataStore(@ApplicationContext context:Context)=TokenDataStore(context)
}