package com.anirudh.demoapplication.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TvShowServices {
    @GET("tv/popular")
    fun getPopularTvshows(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") pageIndex: Int
    ): Call<PopularTvShows?>?
}