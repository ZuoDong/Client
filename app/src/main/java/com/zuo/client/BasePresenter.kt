package com.zuo.client

/**
 * Created by dongdong on 2018/3/10.
 */
interface BasePresenter{
    fun start()
}

interface BaseView<T>{
     var presenter:T
}