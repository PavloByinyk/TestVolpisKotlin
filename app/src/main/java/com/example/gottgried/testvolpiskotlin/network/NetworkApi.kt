package com.example.gottgried.testvolpiskotlin.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Gottgried on 08.10.2017.
 */

class NetworkApi private constructor() {
    private val retrofit: Retrofit

    init {

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create()

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        mRestClient = retrofit.create(RestClient::class.java)
    }

    companion object {

        private var instance: NetworkApi? = null
        private lateinit var mRestClient: RestClient
        private val BASE_URL = "http://zavtrakov.eurodir.ru/"


        @Synchronized
        fun getInstance(): RestClient {
            if (instance == null) {
                instance = NetworkApi()
            }
            return mRestClient
        }
    }
}
