package com.zuo.client.di.modules

import com.zuo.client.di.scop.ActivityScope
import com.zuo.client.httpService.LastfmService
import com.zuo.client.httpService.NetService
import com.zuo.client.mould.MainMould
import com.zuo.client.present.MainPresent
import com.zuo.client.ui.activity.MainActivity
import dagger.Module
import dagger.Provides

/**
 * 作者：zuo
 * 时间：2018/3/14 14:06
 */
@Module
class MainActivityModules(val activity: MainActivity){

    @Provides @ActivityScope
    fun provideNetService(service:LastfmService) = NetService(service)

    @Provides @ActivityScope
    fun provideMainNetModule(netService: NetService) = MainMould(netService)

    @Provides @ActivityScope
    fun provideMainPresenter(mainMould:MainMould) = MainPresent(activity,mainMould)
}