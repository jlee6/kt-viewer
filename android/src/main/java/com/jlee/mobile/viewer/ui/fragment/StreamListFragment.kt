package com.jlee.mobile.viewer.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jlee.mobile.viewer.R
import com.jlee.mobile.viewer.ui.adapter.StreamListAdapter
import kotlinx.android.synthetic.main.fragment_stream_list.*

class StreamListFragment: Fragment() {
    val streamAdapter: StreamListAdapter by lazy { StreamListAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstance: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stream_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstance: Bundle?) {
        super.onViewCreated(view, savedInstance)

        stream_list.adapter = streamAdapter
    }
}