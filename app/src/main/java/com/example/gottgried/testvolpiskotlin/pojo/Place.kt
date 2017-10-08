package com.example.gottgried.testvolpiskotlin.pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by Gottgried on 07.10.2017.
 */

class Place {

    var objectId: String? = null
    var title: String? = null
    var description: String? = null
    var imageUrl: String? = null
    var latitude: Double = 0.toDouble()
    @SerializedName(value = "longitude")
    val longitude: Double = 0.toDouble()

    var isChecked: Boolean = false
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Place

        if (objectId != other.objectId) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (imageUrl != other.imageUrl) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (isChecked != other.isChecked) return false

        return true
    }

    override fun hashCode(): Int {
        var result = objectId?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + isChecked.hashCode()
        return result
    }
}
