package com.zuo.client.di

import com.zuo.client.di.components.MainActivityComponent
import com.zuo.client.di.moudles.MainActivityModules
import javax.inject.Singleton
import dagger.Component

/**
 * 作者：zuo
 * 时间：2018/3/14 17:08
 */
@Singleton
@Component(modules = [AppModule::class,DataModule::class])
interface ApplicationComponent{

    fun plus(module: MainActivityModules): MainActivityComponent
}