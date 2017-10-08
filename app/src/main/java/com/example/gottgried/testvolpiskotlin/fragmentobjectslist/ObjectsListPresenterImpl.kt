package com.example.gottgried.testvolpiskotlin.fragmentobjectslist

import android.content.res.Resources
import com.example.gottgried.testvolpiskotlin.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import com.example.gottgried.testvolpiskotlin.fragmentobjectslist.ObjectsListFragmentContract.*
import com.example.gottgried.testvolpiskotlin.model.DataSaver
import com.example.gottgried.testvolpiskotlin.network.ControllerApi
import com.example.gottgried.testvolpiskotlin.pojo.PlaceResponse

/**
 * Created by Gottgried on 07.10.2017.
 */

class ObjectsListPresenterImpl : MvpBasePresenter<ObjectsListFragmentContract.View>(), Presenter {


    override fun loadPlaces() {
        if (isViewAttached) {
            if (DataSaver.instance.dataIsEmpty()) {
                view.showLoadDialog(true)
                val controllerApi = ControllerApi()
                controllerApi.loadObjectsFromServer(this)
            } else {
                view.showPlaces(DataSaver.instance.getList())
            }
        }
    }

    override fun onSuccessResponce(response: Any) {
        if (isViewAttached) {
            val placeResponse = response as PlaceResponse
            DataSaver.instance.setList(placeResponse.getResult())
            view.showPlaces(DataSaver.instance.getList())
            view.showLoadDialog(false)
            view.showMsg(R.string.msg_update_data_from_serv)
        }
    }

    override fun onFailure(error: String) {
        if (isViewAttached) {
            view.showMsg(error)
        }
    }

    override fun onClickCheckBox(position: Int, isChecked: Boolean) {
        DataSaver.instance.onCheckedPlace(position, isChecked)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        if (retainInstance) {
            DataSaver.instance.clearData()
        }
    }
}
