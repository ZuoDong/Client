package com.zuo.client.present

import android.content.Context
import com.zuo.client.contract.MainContract
import com.zuo.client.mould.MainMould

/**
 * Created by dongdong on 2018/3/11.
 */
class MainPresent(val context:Context): MainContract.Present{

    init {
        val mainMould = MainMould(context)
    }

    override fun start() {

    }

}