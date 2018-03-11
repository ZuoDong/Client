package com.zuo.client

import android.annotation.SuppressLint
import android.app.Application

/**
 * Created by dongdong on 2018/3/11.
 */
class App:Application(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance:Application? = null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}