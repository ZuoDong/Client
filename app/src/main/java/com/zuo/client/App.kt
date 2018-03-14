package com.zuo.client

import android.annotation.SuppressLint
import android.app.Application
import com.zuo.client.di.ApplicationComponent

/**
 * Created by dongdong on 2018/3/11.
 */
class App:Application(){

    companion object {
        lateinit var instance:ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {

    }
}