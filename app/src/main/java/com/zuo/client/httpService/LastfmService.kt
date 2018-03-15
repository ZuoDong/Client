package com.zuo.client.httpService

import com.zuo.client.ui.bean.LastFmBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者：zuo
 * 时间：2018/3/7 17:38
 */
interface LastfmService {

//    @GET("/2.0/?method=artist.search")
//    fun searchArtist(@Query("artist") artist: String): Call<LastFmResponse>
//
//    @GET("/2.0/?method=artist.getinfo")
//    fun requestArtistInfo(@Query("mbid") id: String, @Query("lang") language: String): Call<LastFmResponse>
//
//    @GET("/2.0/?method=artist.gettopalbums")
//    fun requestAlbums(@Query("mbid") id: String, @Query("artist") artist: String): Call<LastFmResponse>

    @GET("/2.0/?method=artist.getsimilar")
    fun requestSimilar(@Query("mbid") id: String): Call<LastFmBean>

//    @GET("/2.0/?method=album.getInfo")
//    fun requestAlbum(@Query("mbid") id: String): Call<LastFmResponse>
}