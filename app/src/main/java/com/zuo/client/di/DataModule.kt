package com.zuo.client.di

import android.content.Context
import com.zuo.client.BuildConfig
import com.zuo.client.Constant
import com.zuo.client.R
import com.zuo.client.di.qualifier.AppQualifier
import com.zuo.client.httpService.LastFmInterceptor
import com.zuo.client.httpService.LastfmService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * 作者：zuo
 * 时间：2018/3/14 16:03
 */
@Module
class DataModule{

    @Provides @Singleton
    fun provideCache(@AppQualifier context:Context):Cache = Cache(context.cacheDir,10*1024*1024.toLong())

    @Provides @Singleton
    fun provideOkhttpClient(@AppQualifier context: Context,cache:Cache):OkHttpClient{
        return OkHttpClient().newBuilder()
                .cache(cache)
                .addInterceptor(LastFmInterceptor(context.getString(R.string.apiKey), Constant.cacheDuration))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()!!
    }

    @Provides @Singleton
    fun provideRetrofit(client:OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides @Singleton
    fun provideLastfmService(retrofit: Retrofit):LastfmService{
        return retrofit.create(LastfmService::class.java)
    }
}