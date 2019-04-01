package com.example.find

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class RecyclerViewAdapter(var context:Context,var listofresults:ArrayList<Data>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.result_showing,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listofresults.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {


    }

    class MyViewHolder(var itemVie:View) : RecyclerView.ViewHolder(itemVie){
        //var labelTextView:TextView = itemVie.findViewById(R.id.label)
        //var browse:ImageView = itemVie.findViewById(R.id.browsee)
    }
}