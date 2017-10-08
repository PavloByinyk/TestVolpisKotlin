package com.example.gottgried.testvolpiskotlin.fragmentmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gottgried.testvolpiskotlin.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.MarkerOptions
import com.hannesdorfmann.mosby3.mvp.MvpFragment

/**
 * Created by Gottgried on 08.10.2017.
 */

class MapFragment : MvpFragment<MapFragmentContract.View, MapFragmentContract.Presenter>(), MapFragmentContract.View, OnMapReadyCallback {


    private var googleMap: GoogleMap? = null
    private var mapView: MapView? = null

    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.map_fragment, container, false)

        rootView!!.findViewById(R.id.btn_show_list).setOnClickListener {
            onClickShowList()
        }

        return rootView
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view!!.findViewById(R.id.my_map) as MapView?
        if (mapView != null) {
            mapView!!.onCreate(null)
            mapView!!.onResume()
            mapView!!.getMapAsync(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPresenter().attachView(this)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(R.string.title_fragment_map)
    }

    fun setToolbarTitle(resId: Int) {
        activity.setTitle(resId)
    }

    override fun onDestroy() {
        super.onDestroy()
        setToolbarTitle(R.string.title_fragment_objects)
        getPresenter().detachView(true)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
        presenter.loadPlaces()
    }

    override fun createPresenter(): MapFragmentContract.Presenter {
        return MapFragmentPresenterImpl()
    }

    override fun setMarkers(markers: List<MarkerOptions>) {
        for (marker in markers) {
            if (googleMap != null) {
                googleMap?.addMarker(marker)
            }
        }
    }

    override fun onClickShowList() {
        fragmentManager.popBackStack()

    }

}
