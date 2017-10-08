package com.example.gottgried.testvolpiskotlin.fragmentmap

import com.google.android.gms.maps.model.MarkerOptions
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Gottgried on 08.10.2017.
 */
interface MapFragmentContract {

    interface View : MvpView {

        fun setMarkers(marker: List<MarkerOptions>)

        fun onClickShowList()

    }

    interface Presenter : MvpPresenter<View> {

        fun loadPlaces()


    }

}