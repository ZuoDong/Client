package com.zuo.client.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.zuo.client.R
import com.zuo.client.adapter.ArtistsAdapter
import com.zuo.client.bean.LastFmBean
import com.zuo.client.contract.MainContract
import com.zuo.client.httpService.ClientResponse
import com.zuo.client.httpService.NetService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {


    override lateinit var presenter: MainContract.Present

    val adapter: ArtistsAdapter by lazy { ArtistsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        requestData()
    }

    private fun requestData() {
        NetService.requestNet({ service ->
            service.requestSimilar(getString(R.string.coldplayMbid))
        },object :ClientResponse<LastFmBean>{
            override fun onSuccess(body: LastFmBean?) {
                val artists = body?.similarartists?.artist?:return
                progressbar.visibility = View.GONE
                adapter.addData(artists)
            }

            override fun onFailure(t: Throwable?) {
                progressbar.visibility = View.GONE
            }

        })
    }

    private fun initView() {
        artistsRecyclerView.adapter = adapter
        artistsRecyclerView.layoutManager = GridLayoutManager(this,2)

        progressbar.visibility = View.VISIBLE
    }
}
