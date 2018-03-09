package com.zuo.client

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val adapter:ArtistsAdapter by lazy { ArtistsAdapter()}

    companion object {
        val apiKey = "84eda91738849cc300c34506a6e013b5"
        val cacheDuration:Int = 86400
        val coldplayMbid = "cc197bad-dc9c-440d-a5b5-d52ba2e14234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        request()
    }

    private fun initView() {
        artistsRecyclerView.adapter = adapter
        artistsRecyclerView.layoutManager = GridLayoutManager(this,2)

        progressbar.visibility = View.VISIBLE
    }

    private fun request(){

        val cache = Cache(cacheDir, 10 * 1024 * 1024.toLong())

        val client = OkHttpClient().newBuilder()
                .cache(cache)
                .addInterceptor(LastFmInterceptor(apiKey,cacheDuration))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(LastfmService::class.java)
        val requestData = service.requestSimilar(coldplayMbid)
        requestData.enqueue(object :Callback<LastFmBean>{
            override fun onResponse(call: Call<LastFmBean>?, response: retrofit2.Response<LastFmBean>?) {
                println("请求成功")
                val artists = response?.body()?.similarartists?.artist?:return
                progressbar.visibility = View.GONE
                adapter.addData(artists)
            }

            override fun onFailure(call: Call<LastFmBean>?, t: Throwable?) {
                println("请求失败")
                progressbar.visibility = View.GONE
            }

        })
    }
}

class LastFmInterceptor(private val apiKey: String?,private val cacheDuration: Int) :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("format", "json")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .addHeader("Cache-Control", "public, max-age=$cacheDuration")
                .build()

        return chain.proceed(newRequest)
    }
}
