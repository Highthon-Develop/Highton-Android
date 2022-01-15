package com.example.highton_android.di

import com.example.highton_android.data.TokenDataStore
import com.example.highton_android.data.repository.AuthRepository
import com.example.highton_android.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(dataStore : TokenDataStore,authApi:AuthService)=AuthRepository(dataStore,authApi)


}