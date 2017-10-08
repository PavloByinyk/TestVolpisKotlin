package com.example.gottgried.testvolpiskotlin.model

import android.location.Location
import com.example.gottgried.testvolpiskotlin.pojo.Place
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.util.ArrayList
import java.util.LinkedList

/**
 * Created by Gottgried on 08.10.2017.
 */

class DataSaver private constructor() {
    private val list: MutableList<Place>


    private val myLocation: String? = null

    var mLocation: Location? = null


    init {
        list = ArrayList()
    }

    fun setList(list: List<Place>) {
        if (dataIsEmpty()) {
            this.list.clear()
        }
        this.list.addAll(list)
    }

    fun getList(): List<Place> {
        return list
    }

    fun onCheckedPlace(position: Int, isChecked: Boolean) {
        this.list[position].isChecked = isChecked
    }

    fun clearData() {
        this.list.clear()
    }

    fun dataIsEmpty(): Boolean {
        return this.list.isEmpty()
    }


    companion object {

        private var dataSaver: DataSaver? = null

        val instance: DataSaver
            @Synchronized get() {
                if (dataSaver == null) {
                    dataSaver = DataSaver()
                }
                return dataSaver!!
            }
    }

    val checkedList: List<MarkerOptions>
        get() {

            var markersList = LinkedList<MarkerOptions>()
            for (place in this.list) {
                if (place.isChecked) {
                    val pos = LatLng(place.latitude, place.longitude)
                    markersList.add(MarkerOptions().position(pos).title(place.title))
                }
            }
            return markersList
        }


}
