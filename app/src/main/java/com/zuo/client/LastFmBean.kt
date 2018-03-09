package com.zuo.client

import com.google.gson.annotations.SerializedName

/**
 * 作者：zuo
 * 时间：2018/3/7 18:22
 */

data class LastFmBean(
		val similarartists: Similarartists
)

data class Similarartists(
		val artist: List<Artist>
)

data class Artist(
		val name: String,
		val mbid: String,
		val match: String,
		val url: String,
		val image: List<Image>,
		val streamable: String
)

data class Image(
        @SerializedName("#text") val imgurl: String,
        val size: String
)