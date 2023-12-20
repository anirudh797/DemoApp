package com.anirudh.demoapplication.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object TvShowApi {
    private var retrofit: Retrofit? = null
    val API_KEY = "cc602f307cfc40b34e7a4a4c897ae62b"
    val CATEGORY = "popular"
    val LANGUAGE = "en-US"
    val PAGE = 1
    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    private var tvShowInterface = retrofit?.create(TvShowServices::class.java)

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build()
            }
            return retrofit
        }

      var call = tvShowInterface?.getPopularTvshows(API_KEY, LANGUAGE, PAGE)

}