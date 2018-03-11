package com.zuo.client.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * 作者：zuo
 * 时间：2018/3/9 10:34
 */
fun ImageView.loadUrl(url:String){
    Glide.with(context).load(url).into(this)
}