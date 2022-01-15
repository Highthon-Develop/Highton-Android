package com.example.highton_android.di

import com.example.highton_android.data.TokenDataStore
import com.example.highton_android.data.repository.AuthRepository
import com.example.highton_android.data.repository.DiaryRepository
import com.example.highton_android.data.repository.FeedRepository
import com.example.highton_android.data.service.AuthService
import com.example.highton_android.data.service.DiaryService
import com.example.highton_android.data.service.FeedService
import com.example.highton_android.data.service.SearchSchoolService
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
    fun provideDiaryRepository(api:DiaryService)=DiaryRepository(api)

    @Provides
    @Singleton
    fun provideAuthRepository(dataStore:TokenDataStore,api:AuthService,searchSchoolApi:SearchSchoolService)=AuthRepository(dataStore,api,searchSchoolApi)

    @Provides
    @Singleton
    fun provideFeedRepository(api:FeedService)=FeedRepository(api)
}