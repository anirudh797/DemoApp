package com.anirudh.demoapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.anirudh.demoapplication.R
import com.anirudh.demoapplication.data.TvShowApi
import com.anirudh.demoapplication.data.TvShowServices

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class MainActivity : AppCompatActivity() {


    val greeting = "This is a string"
    val greetings = arrayListOf("abc", "bcs", "cdf")
    val TAG = "Anirudh"
    var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    lateinit var myObserver: DisposableObserver<String>
    private lateinit var myObserver2: DisposableObserver<String>
    private lateinit var textView: TextView
    private var allPopulartvShowssApi: TvShowServices? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, " onCreate")
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.tvGreeting)
        val myObservable: Observable<ArrayList<String>> = Observable.fromArray(greetings)
//        myObserver = object : DisposableObserver<String>() {
//            override fun onNext(t: String) {
//                Log.i(TAG, " onNext invoked")
//                textView.text = t
//            }
//
//            override fun onError(e: Throwable) {
//                Log.i(TAG, "onError invoked")
//            }
//
//            override fun onComplete() {
//                Log.i(TAG, "onComplete invoked")
//            }
//        }
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
        allPopulartvShowssApi = TvShowApi.client?.create(TvShowServices::class.java)
        allPopulartvShowssApi.
//
//        compositeDisposable?.add(
//            myObservable.subscribeOn(Schedulers.io()).observeOn(
//                AndroidSchedulers.mainThread()
//            ).subscribeWith(myObserver2)
//        )
//
//        myObservable.subscribeOn(Schedulers.io()).observeOn(
//            AndroidSchedulers.mainThread()
//        ).subscribeWith(myObserver2)

    }

    override fun onDestroy() {
        super.onDestroy()
//        disposable?.dispose()
        compositeDisposable?.clear()

    }
}