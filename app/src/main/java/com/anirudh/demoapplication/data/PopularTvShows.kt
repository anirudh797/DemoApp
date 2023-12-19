package com.anirudh.demoapplication.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PopularTvShows(
    @SerializedName("page")
    @Expose
    var page: Int? = null,

    @SerializedName("results")
    @Expose
    var results: java.util.ArrayList<Result>? = ArrayList(),

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createTypedArrayList(Result),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(page)
        parcel.writeTypedList(results)
        parcel.writeValue(totalResults)
        parcel.writeValue(totalPages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularTvShows> {
        override fun createFromParcel(parcel: Parcel): PopularTvShows {
            return PopularTvShows(parcel)
        }

        override fun newArray(size: Int): Array<PopularTvShows?> {
            return arrayOfNulls(size)
        }
    }

}


