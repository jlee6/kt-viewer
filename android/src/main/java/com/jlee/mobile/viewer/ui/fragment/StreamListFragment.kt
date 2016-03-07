package com.jlee.mobile.viewer.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jlee.mobile.viewer.R

class StreamListFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstance: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_stream_list, container, false)
        return view
    }
}