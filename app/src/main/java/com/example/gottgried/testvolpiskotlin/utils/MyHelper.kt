package com.example.gottgried.testvolpiskotlin.utils

import com.example.gottgried.testvolpiskotlin.pojo.Place
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

/**
 * Created by Gottgried on 08.10.2017.
 */

object MyHelper {

    fun distanceKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): String {
        var lat1 = lat1
        var lat2 = lat2
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        lat1 = Math.toRadians(lat1)
        lat2 = Math.toRadians(lat2)

        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2)

        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        val formatter = DecimalFormat("#0.0")
        val R = 6371
        return formatter.format(R * c)
    }

}
