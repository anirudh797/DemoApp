package com.anirudh.demoapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anirudh.demoapplication.R
import com.anirudh.demoapplication.data.PopularTvShows
import com.anirudh.demoapplication.data.TvShowApi
import com.anirudh.demoapplication.data.TvShowServices

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import retrofit2.Call
import retrofit2.Response
import retrofit2.create


class MainActivity : AppCompatActivity() {


    val greeting = "This is a string"
    val greetings = arrayListOf("abc", "bcs", "cdf")
    val TAG = "Anirudh"
    var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    lateinit var myObserver: DisposableObserver<String>
    private lateinit var myObserver2: DisposableObserver<String>
    private lateinit var textView: TextView
    private lateinit var rv: RecyclerView
    private lateinit var tvShowsAdapter: ShowsAdapter
    private var allPopulartvShowssApi: TvShowServices? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, " onCreate")
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tvGreeting)
        rv = findViewById<RecyclerView>(R.id.rv)
        val myObservable: Observable<ArrayList<String>> = Observable.fromArray(greetings)
        myObserver2 = object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                Log.i(TAG, "onNext invoked")
//                Thread.sleep(1000)
                textView.text = t
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError invoked $e")
            }

            override fun onComplete() {
                Log.i(TAG, "onComplete invoked")
            }
        }
        tvShowsAdapter = ShowsAdapter()
        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = tvShowsAdapter
        }
        allPopulartvShowssApi = TvShowApi.client?.create(TvShowServices::class.java)


        allPopulartvShowssApi?.getPopularTvshows(
            TvShowApi.API_KEY,
            TvShowApi.LANGUAGE,
            TvShowApi.PAGE
        )?.enqueue(
            object : retrofit2.Callback<PopularTvShows?> {
                override fun onResponse(
                    call: Call<PopularTvShows?>,
                    response: Response<PopularTvShows?>
                ) {
                    val movieResults = response.body()
                    val movies = movieResults?.results
                    if (movies != null) {
                        tvShowsAdapter.setTvShows(movies)
                        tvShowsAdapter.notifyDataSetChanged()
                    }

                }

                override fun onFailure(call: Call<PopularTvShows?>, t: Throwable) {
                    t.printStackTrace()
                }
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
//        disposable?.dispose()
        compositeDisposable?.clear()
    }
}

