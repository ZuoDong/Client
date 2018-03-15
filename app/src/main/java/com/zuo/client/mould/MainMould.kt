package com.zuo.client.mould

import com.zuo.client.App
import com.zuo.client.R
import com.zuo.client.ui.bean.LastFmBean
import com.zuo.client.httpService.ClientResponse
import com.zuo.client.httpService.NetService

/**
 * Created by dongdong on 2018/3/11.
 */
class MainMould(val netService:NetService){

    fun requestSmilar(result:ClientResponse<LastFmBean>) {
        netService.requestNet({ service ->
            service.requestSimilar(App.instance.getString(R.string.coldplayMbid))
        },result)
    }
}