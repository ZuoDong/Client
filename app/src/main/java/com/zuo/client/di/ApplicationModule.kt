package com.zuo.client.di

import android.content.Context
import com.zuo.client.App
import com.zuo.client.di.qualifier.AppQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 作者：zuo
 * 时间：2018/3/14 15:14
 */
@Module
class ApplicationModule(private val app: App){

    @Provides @Singleton
    fun provideApplication():App = app

    @Provides @Singleton @AppQualifier
    fun provideApplicationContext():Context = app

    @Provides
    fun provideHello() = "HelloWorld"
}