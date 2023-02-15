package com.me.myquran.data.module


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.me.myquran.data.remote.api.EQuranApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    var API_BASE_URL = "https://equran.id/api/v2/"

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): EQuranApi {
        return Retrofit.Builder().apply {
            baseUrl(API_BASE_URL)
            addConverterFactory(GsonConverterFactory.create(getGson()))
            client(okHttp)
        }.build().create(EQuranApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

}