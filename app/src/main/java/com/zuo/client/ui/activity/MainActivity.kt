package com.zuo.client.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.zuo.client.App
import com.zuo.client.R
import com.zuo.client.ui.adapter.ArtistsAdapter
import com.zuo.client.ui.bean.Artist
import com.zuo.client.contract.MainContract
import com.zuo.client.di.moudles.MainActivityModules
import com.zuo.client.present.MainPresent
import com.zuo.client.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainPresent

    val adapter: ArtistsAdapter = ArtistsAdapter(object :ArtistsAdapter.OnArtistClickListener{
        override fun clickArtist(artist: Artist, position: Int) {
            navigate(artist,position)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.component.plus(MainActivityModules(this)).inject(this)
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
