package com.example.gottgried.testvolpiskotlin.fragmentobjectslist

import com.example.gottgried.testvolpiskotlin.pojo.Place
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Gottgried on 07.10.2017.
 */

interface ObjectsListFragmentContract {
    interface View : MvpView {

        fun showPlaces(list: List<Place>)

        fun onClickShowOnMap()

        fun showLoadDialog(show: Boolean)

        fun showMsg(msg: String)

        fun showMsg(msg: Int)

        fun notifyListData()
    }

    interface Presenter : MvpPresenter<View> {

        fun loadPlaces()

        fun onSuccessResponce(response: Any)

        fun onFailure(error: String)

        fun onClickCheckBox(position: Int, isChecked: Boolean)

    }
}
