package com.example.gottgried.testvolpiskotlin.network

import com.example.gottgried.testvolpiskotlin.pojo.PlaceResponse

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Gottgried on 08.10.2017.
 */

interface RestClient {

    @GET("response.json")
    fun getData(): Call<PlaceResponse>
}
