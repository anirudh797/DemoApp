package com.anirudh.demoapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.demoapplication.R
import com.anirudh.demoapplication.data.Result

class ShowsAdapter(private var tvShowResults: ArrayList<Result>) :
    RecyclerView.Adapter<ShowsAdapter.ShowViewHolder>() {


    private val BASE_URL_IMG = "https://image.tmdb.org/t/p/w300"
    private val BASE_URL_IMG_BACKGROUND = "https://image.tmdb.org/t/p/w780"

    fun setTvShows(list: ArrayList<Result>) {
        this.tvShowResults = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ShowViewHolder(view)
    }


    override fun getItemCount(): Int {
        return tvShowResults.size
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {

        val item = tvShowResults[position]
        holder.bind(item)

    }

    inner class ShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tv: TextView = itemView.findViewById(R.id.tv)
        fun bind(item: Result) {
            tv.text = item.originalTitle
        }

    }
}