package com.example.gottgried.testvolpiskotlin.adapter

import android.content.Context
import android.location.Location
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.gottgried.testvolpiskotlin.R
import com.example.gottgried.testvolpiskotlin.model.DataSaver
import com.example.gottgried.testvolpiskotlin.fragmentobjectslist.ObjectsListFragmentContract
import com.example.gottgried.testvolpiskotlin.pojo.Place
import com.example.gottgried.testvolpiskotlin.utils.MyHelper

import java.util.ArrayList

/**
 * Created by Gottgried on 08.10.2017.
 */

class PlaceAdapter(private val context: Context, var mLocation: Location?, var presenter: ObjectsListFragmentContract.Presenter) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private val objectList: MutableList<Place>?

    init {
        this.objectList = ArrayList()
    }

    fun setObjectList(objectList: List<Place>?) {
        if (objectList != null && objectList.size > 0) {
            this.objectList!!.clear()
            this.objectList.addAll(objectList)
            notifyDataSetChanged()
        }
    }

    fun notifyListData() {
        mLocation = DataSaver.instance.mLocation
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(v)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(objectList!![position])
    }

    override fun getItemCount(): Int {
        return objectList?.size ?: 0
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.findViewById(R.id.tv_item_title) as? TextView
        var tvDescription = itemView.findViewById(R.id.tv_item_description) as? TextView
        var tvDistance = itemView.findViewById(R.id.tv_item_distance) as? TextView
        var ivItemPlacePhoto = itemView.findViewById(R.id.iv_item_place) as? ImageView
        var checkBox = itemView.findViewById(R.id.checkBox_item) as? CheckBox

        fun bind(placeObject: Place) {
            tvTitle!!.text = placeObject.title
            tvDescription!!.text = placeObject.description
            checkBox!!.isChecked = placeObject.isChecked
            Glide.with(context).load(placeObject.imageUrl).into(ivItemPlacePhoto)

            checkBox!!.setOnCheckedChangeListener { _, isChecked ->
                objectList!![adapterPosition].isChecked = isChecked
                presenter.onClickCheckBox(adapterPosition, isChecked)
            }

            if (mLocation != null) {
                tvDistance!!.setText(MyHelper.distanceKm(mLocation!!.latitude, mLocation!!.longitude, placeObject.latitude, placeObject.longitude))
            }

        }

    }

}
