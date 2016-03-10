package com.jlee.mobile.viewer.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jlee.mobile.viewer.R
import com.jlee.mobile.viewer.model.Endpoint

class StreamListAdapter: RecyclerView.Adapter<StreamListAdapter.ViewHolder>() {
    lateinit var streamList: Array<Endpoint>

    override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): ViewHolder? {
        var view = LayoutInflater
                .from(viewGroup?.context)
                .inflate(R.layout.layout_stream_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        view.title.text = streamList[position].name
        view.description.text = streamList[position].desc
    }

    override fun getItemCount(): Int {
        return streamList.size
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val context by lazy { view.context }

        val title: TextView by lazy { view.findViewById(R.id.item_title) as TextView }
        val description: TextView by lazy { view.findViewById(R.id.item_description) as TextView }
    }
}