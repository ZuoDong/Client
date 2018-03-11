package com.zuo.client.contract

import com.zuo.client.BasePresenter
import com.zuo.client.BaseView

/**
 * Created by dongdong on 2018/3/11.
 */
interface MainContract{

    interface View: BaseView<Present> {

    }

    interface Present: BasePresenter {

    }
}