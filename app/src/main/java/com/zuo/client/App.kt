package com.zuo.client

import android.app.Application
import com.zuo.client.di.ApplicationComponent
import com.zuo.client.di.ApplicationModule
import com.zuo.client.di.DaggerApplicationComponent

/**
 * Created by dongdong on 2018/3/11.
 */
class App:Application(){

    companion object {
        lateinit var component:ApplicationComponent
        lateinit var instance:App
    }

    override fun onCreate() {
        super.onCreate()
        App.instance = this
        initializeDagger()
    }

    private fun initializeDagger() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}