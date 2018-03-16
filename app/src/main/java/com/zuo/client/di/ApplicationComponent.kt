package com.zuo.client.di

import com.zuo.client.di.components.MainActivityComponent
import com.zuo.client.di.modules.MainActivityModules
import javax.inject.Singleton
import dagger.Component

/**
 * 作者：zuo
 * 时间：2018/3/14 17:08
 */
@Singleton
@Component(modules = [(ApplicationModule::class),(DataModule::class)])
interface ApplicationComponent{
    fun plus(mainModule: MainActivityModules): MainActivityComponent
}