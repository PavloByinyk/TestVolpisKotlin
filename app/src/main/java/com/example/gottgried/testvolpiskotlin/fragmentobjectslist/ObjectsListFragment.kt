package com.example.gottgried.testvolpiskotlin.fragmentobjectslist

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.gottgried.testvolpiskotlin.R
import com.example.gottgried.testvolpiskotlin.adapter.PlaceAdapter
import com.example.gottgried.testvolpiskotlin.fragmentmap.MapFragment
import com.example.gottgried.testvolpiskotlin.model.DataSaver
import com.example.gottgried.testvolpiskotlin.pojo.Place
import com.example.gottgried.testvolpiskotlin.utils.MyConstants
import com.hannesdorfmann.mosby3.mvp.MvpFragment

/**
 * Created by Gottgried on 07.10.2017.
 */

class ObjectsListFragment : MvpFragment<ObjectsListFragmentContract.View, ObjectsListFragmentContract.Presenter>(), ObjectsListFragmentContract.View {


    private var adapter: PlaceAdapter? = null
    private lateinit var progressDialog: ProgressDialog
    private var recyclerView: RecyclerView? = null

    companion object {
        fun newInstance(): ObjectsListFragment {
            return ObjectsListFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        initAdapter()
        getPresenter().loadPlaces()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getPresenter().attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.object_list_fragment, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view) as RecyclerView?
        progressDialog = ProgressDialog(activity)
        rootView!!.findViewById(R.id.btn_show_on_map).setOnClickListener {
            onClickShowOnMap()
        }

        return rootView
    }

    fun setToolbarTitle() {
        activity.setTitle(R.string.title_fragment_objects)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }

    private fun initAdapter() {
        adapter = PlaceAdapter(activity, DataSaver.instance.mLocation, getPresenter())
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setAdapter(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().detachView(true)
    }

    override fun showPlaces(list: List<Place>) {
        adapter!!.setObjectList(list)
    }

    override fun onClickShowOnMap() {
        val fm = fragmentManager
        val ft = fm.beginTransaction()
        val map = MapFragment.newInstance()
        map.setTargetFragment(this, MyConstants.MY_FRAGMENT_RESPONSE);
        ft.add(R.id.cont_objects_list, map)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun showLoadDialog(show: Boolean) {
        if (show && !progressDialog.isShowing()) {
            progressDialog.setMessage("Loading data, please wait")
            progressDialog.setCancelable(false)
            progressDialog.show()
        } else if (!show && progressDialog.isShowing()) {
            progressDialog.dismiss()
        }
    }

    override fun showMsg(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
    override fun showMsg(msg: Int) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun notifyListData() {
        if (adapter != null)
            adapter?.notifyListData()
    }

    override fun createPresenter(): ObjectsListFragmentContract.Presenter {
        return ObjectsListPresenterImpl()
    }

}
