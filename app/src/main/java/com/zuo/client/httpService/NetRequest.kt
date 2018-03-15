package com.zuo.client.httpService

import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

/**
 * Created by dongdong on 2018/3/11.
 */
class NetService(val service:LastfmService){

    fun <T> requestNet(dorequest:(cal:LastfmService) -> Call<T>, result:ClientResponse<T>){
        val requestData = dorequest(service)
        requestData.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: retrofit2.Response<T>?) {
                println("请求成功")
                result.onSuccess(response?.body())
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                println("请求失败")
                result.onFailure(t)
            }

        })
    }

}

interface ClientResponse<in T>{
    fun onSuccess(body: T?)
    fun onFailure(t: Throwable?)
}