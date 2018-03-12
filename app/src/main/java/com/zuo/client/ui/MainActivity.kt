package com.zuo.client.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.zuo.client.R
import com.zuo.client.adapter.ArtistsAdapter
import com.zuo.client.bean.Artist
import com.zuo.client.contract.MainContract
import com.zuo.client.present.MainPresent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override val presenter: MainContract.Present = MainPresent(this)

    val adapter: ArtistsAdapter = ArtistsAdapter(object :ArtistsAdapter.OnArtistClickListener{
        override fun clickArtist(artist: Artist, position: Int) {
            navigate(artist,position)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    private fun initView() {
        artistsRecyclerView.adapter = adapter
        artistsRecyclerView.layoutManager = GridLayoutManager(this,2)

        progressbar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        progressbar.visibility = View.GONE
    }

    override fun resultSimilar(list: List<Artist>) {
        adapter.addData(list)
    }

    override fun navigate(artist: Artist, position: Int) {
        Toast.makeText(this,"点击${position},转跳到${artist.name}详情", Toast.LENGTH_SHORT).show()
    }
}
