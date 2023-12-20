package com.anirudh.demoapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.demoapplication.R
import com.anirudh.demoapplication.data.Result
import com.squareup.picasso.Picasso

class ShowsAdapter() :
    RecyclerView.Adapter<ShowsAdapter.ShowViewHolder>() {


    var results : ArrayList<Result>   = arrayListOf()
    private val BASE_URL_IMG = "https://image.tmdb.org/t/p/w300"
    private val BASE_URL_IMG_BACKGROUND = "https://image.tmdb.org/t/p/w780"

    fun setTvShows(list: ArrayList<Result>) {
        this.results = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ShowViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val item = results[position]
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    inner class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tv: TextView = itemView.findViewById(R.id.tv)
        private var iv: ImageView = itemView.findViewById(R.id.iv)
        fun bind(item: Result) {
            tv.text = item.originalTitle
            val imageUrl = "https://api.themoviedb.org/3/"+item.posterPath?.subSequence(1,item.posterPath?.length ?:0 -1)

            Picasso.get().load(imageUrl).into(iv)
        }

    }
}