package com.zuo.client.contract

import com.zuo.client.BasePresenter
import com.zuo.client.BaseView
import com.zuo.client.ui.bean.Artist

/**
 * Created by dongdong on 2018/3/11.
 */
interface MainContract{

    interface View: BaseView{
        fun dismissLoading()
        fun resultSimilar(list:List<Artist>)
        fun navigate(artist:Artist,position: Int)
    }

    interface Present: BasePresenter {

    }
}