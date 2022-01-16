package com.example.highton_android.di


import com.example.highton_android.data.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  
    private const val BASE_URL = "http://ec2-15-165-31-31.ap-northeast-2.compute.amazonaws.com:3000/"
    private const val SCHOOL_BASE_URL = "https://open.neis.go.kr/"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //서버로부터의 응답까지의 시간이 읽기 시간 초과보다 크면 요청 실패로 판단한다.
            .readTimeout(30, TimeUnit.SECONDS)
            //서버로 요청을 시작한 후 15초가 지날 때 까지 데이터가 안오면 에러로 판단한다.
            .connectTimeout(30, TimeUnit.SECONDS)

            // 얼마나 빨리 서버로 데이터를 받을 수 있는지 판단한다.
            .writeTimeout(30, TimeUnit.SECONDS)
            .// 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
            addInterceptor(getLoggingInterceptor())


            .build()

    }

    @Named("main")
    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            //json 변화기 Factory

            .client(provideHttpClient())

            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Named("school")
    @Singleton
    @Provides
    fun provideSchoolInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SCHOOL_BASE_URL)
            .client(okHttpClient)
            //json 변화기 Factory

            .client(provideHttpClient())

            .addConverterFactory(gsonConverterFactory)
            .build()
    }



    @Provides
    @Singleton
    fun provideMealService( @Named("school")retrofit: Retrofit): MealService {
        return retrofit.create(MealService::class.java)
    }

    @Provides
    @Singleton
    fun provideDiaryService( @Named("main")retrofit: Retrofit): DiaryService {
        return retrofit.create(DiaryService::class.java)
    }
    @Provides
    @Singleton
    fun provideAuthService( @Named("main")retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
    @Provides
    @Singleton
    fun provideSearchSchoolService( @Named("school")retrofit: Retrofit): SearchSchoolService {
        return retrofit.create(SearchSchoolService::class.java)
    }


    @Provides
    @Singleton
    fun provideFeedService( @Named("main")retrofit: Retrofit): FeedService {
        return retrofit.create(FeedService::class.java)
    }


    @Provides
    @Singleton
    fun provideHomeService( @Named("main")retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }



    // 서버로 부터 받아온 데이터 log 찍기
    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}
