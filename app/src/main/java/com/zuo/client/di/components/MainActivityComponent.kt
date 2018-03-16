package com.zuo.client.di.components

import com.zuo.client.di.modules.MainActivityModules
import com.zuo.client.di.scop.ActivityScope
import com.zuo.client.ui.activity.MainActivity
import dagger.Subcomponent

/**
 * 作者：zuo
 * 时间：2018/3/14 14:05
 */
@ActivityScope
@Subcomponent(modules = [MainActivityModules::class])
interface MainActivityComponent{
    fun inject(activity: MainActivity)
}