package com.anirudh.demoapplication.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("first_air_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("original_name")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("name")
    @Expose
    var title: String? = null,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null,
) : Parcelable {


    constructor(parcel: Parcel) : this() {
        posterPath = parcel.readString()
        overview = parcel.readString()
        releaseDate = parcel.readString()
        originalTitle = parcel.readString()
        title = parcel.readString()
        backdropPath = parcel.readString()
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun describeContents(): Int {

        return 0;
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(posterPath)
        dest.writeString(overview)
        dest.writeString(releaseDate)
        dest.writeString(originalTitle)
        dest.writeString(title)
        dest.writeString(backdropPath)
        dest.writeValue(voteAverage)
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}
