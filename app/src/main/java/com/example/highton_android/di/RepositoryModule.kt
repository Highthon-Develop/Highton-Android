package com.example.highton_android.di

import com.example.highton_android.data.repository.DiaryRepository
import com.example.highton_android.data.service.DiaryService
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

//    @Provides
//    @Singleton
//    fun provideFeedRepository(api:DiaryService)=DiaryRepository(api)
}