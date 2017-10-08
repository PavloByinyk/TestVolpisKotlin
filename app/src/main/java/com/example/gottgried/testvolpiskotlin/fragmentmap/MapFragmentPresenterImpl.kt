package com.example.gottgried.testvolpiskotlin.fragmentmap

import com.example.gottgried.testvolpiskotlin.model.DataSaver
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

/**
 * Created by Gottgried on 08.10.2017.
 */

class MapFragmentPresenterImpl : MvpBasePresenter<MapFragmentContract.View>(), MapFragmentContract.Presenter {


    override fun loadPlaces() {
        if (isViewAttached) {
            view.setMarkers(DataSaver.instance.checkedList)
        }

    }


}
