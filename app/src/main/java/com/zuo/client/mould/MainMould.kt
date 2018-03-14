package com.zuo.client.mould

import android.content.Context
import com.zuo.client.App
import com.zuo.client.R
import com.zuo.client.bean.LastFmBean
import com.zuo.client.httpService.ClientResponse
import com.zuo.client.httpService.NetService

/**
 * Created by dongdong on 2018/3/11.
 */
class MainMould{

    fun requestSmilar(result:ClientResponse<LastFmBean>) {
//        NetService.requestNet({ service ->
//            service.requestSimilar(App.instance().getString(R.string.coldplayMbid))
//        },result)
    }
}