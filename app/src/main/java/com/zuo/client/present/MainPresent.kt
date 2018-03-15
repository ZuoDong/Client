package com.zuo.client.present

import com.zuo.client.ui.bean.LastFmBean
import com.zuo.client.contract.MainContract
import com.zuo.client.httpService.ClientResponse
import com.zuo.client.mould.MainMould

/**
 * Created by dongdong on 2018/3/11.
 */
class MainPresent(val view:MainContract.View,val mainMould:MainMould): MainContract.Present{

    override fun start() {
        requestData()
    }

    private fun requestData() {
        mainMould.requestSmilar(object : ClientResponse<LastFmBean> {
            override fun onSuccess(body: LastFmBean?) {
                val artists = body?.similarartists?.artist?:return
                view.dismissLoading()
                view.resultSimilar(artists)
            }

            override fun onFailure(t: Throwable?) {
                view.dismissLoading()
            }

        })
    }


}