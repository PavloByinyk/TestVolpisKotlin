package com.example.gottgried.testvolpiskotlin.network

import com.example.gottgried.testvolpiskotlin.fragmentobjectslist.ObjectsListFragmentContract
import com.example.gottgried.testvolpiskotlin.pojo.PlaceResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Gottgried on 08.10.2017.
 */

class ControllerApi constructor() {

    fun loadObjectsFromServer(presenter: ObjectsListFragmentContract.Presenter) {

        NetworkApi.getInstance().getData().enqueue(object : Callback<PlaceResponse> {
            override fun onResponse(call: Call<PlaceResponse>, response: Response<PlaceResponse>) {
                if (response.isSuccessful) {
                    presenter.onSuccessResponce(response.body())
                } else {
                    presenter.onFailure(response.message().toString())
                }
            }

            override fun onFailure(call: Call<PlaceResponse>, t: Throwable) {
                presenter.onFailure(t.message!!)
            }
        })
    }
}
