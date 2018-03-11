package com.zuo.client.httpService

import com.zuo.client.App
import com.zuo.client.BuildConfig
import com.zuo.client.Constant
import com.zuo.client.R
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dongdong on 2018/3/11.
 */
object NetService{

    private val cache = Cache(App.instance().cacheDir, 10 * 1024 * 1024.toLong())

    private val client = OkHttpClient().newBuilder()
            .cache(cache)
            .addInterceptor(LastFmInterceptor(App.instance().getString(R.string.apiKey), Constant.cacheDuration))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()!!

    private val retrofit = Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val service = retrofit.create(LastfmService::class.java)

    fun <T> requestNet(dorequest:(cal:LastfmService) -> Call<T>, result:ClientResponse<T>){
        val requestData = dorequest(service)
        requestData.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: retrofit2.Response<T>?) {
                println("请求成功")
                result.onSuccess(response?.body())
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                println("请求失败")
                result.onFailure(t)
            }

        })
    }

}

interface ClientResponse<in T>{
    fun onSuccess(body: T?)
    fun onFailure(t: Throwable?)
}