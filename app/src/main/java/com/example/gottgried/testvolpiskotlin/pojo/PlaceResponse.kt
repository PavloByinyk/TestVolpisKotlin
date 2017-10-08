package com.example.gottgried.testvolpiskotlin.pojo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Gottgried on 07.10.2017.
 */

class PlaceResponse : Parcelable {

    internal lateinit var result: List<Place>

    protected constructor() {}

    constructor(result: List<Place>) {
        this.result = result
    }

    fun getResult(): List<Place> {
        return result
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {}

    companion object {


        val CREATOR: Parcelable.Creator<PlaceResponse> = object : Parcelable.Creator<PlaceResponse> {
            override fun createFromParcel(`in`: Parcel): PlaceResponse {
                return PlaceResponse()
            }

            override fun newArray(size: Int): Array<PlaceResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}
