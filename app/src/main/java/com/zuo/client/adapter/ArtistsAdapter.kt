package com.zuo.client.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.zuo.client.R
import com.zuo.client.bean.Artist
import com.zuo.client.extensions.loadUrl
import kotlinx.android.synthetic.main.item_artist.view.*

/**
 * 作者：zuo
 * 时间：2018/3/8 18:00
 */
class ArtistsAdapter: RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    var artistorsList = ArrayList<Artist>()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindArtist(artistorsList[position])
    }

    override fun getItemCount(): Int {
        return artistorsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = View.inflate(parent?.context, R.layout.item_artist,null)
        return ViewHolder(view)
    }

    fun addData(list:List<Artist>){
        if(list.isNotEmpty()){
            artistorsList.addAll(list)
            notifyDataSetChanged()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindArtist(artist: Artist){
            itemView.item_image.loadUrl(artist.image[2].imgurl)
            itemView.item_name.text = artist.name
        }
    }
}