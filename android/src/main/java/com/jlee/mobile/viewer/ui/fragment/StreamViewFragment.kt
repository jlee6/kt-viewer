package com.jlee.mobile.viewer.ui.fragment

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView

import com.jlee.mobile.viewer.R

class StreamViewFragment: Fragment() {
    /*
     * Throws QCMediaPlayer not present - samsung
     * val RTSP_STREAM = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
     */
    val HLS_STREAM = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"
//            = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"

    lateinit var stream: VideoView
    lateinit var controller: MediaController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstance: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stream_view, container, false)

        stream = view.findViewById(R.id.video_stream_viewer) as VideoView

        initializeVideoView()

        return view
    }

   override fun onStart() {
        super.onStart()

        if (stream.isPlaying) {
            return
        }

        stream.start()
    }

    override fun onPause() {
        super.onPause()

        stream.stopPlayback();
    }

    private fun initializeVideoView() {
        controller = MediaController(activity)
        stream.setMediaController(controller)

        stream.setVideoPath(HLS_STREAM)

        stream.setOnErrorListener { player, error, extra ->
            Log.wtf("StreamView", "Stream thrown error $error: $extra")

            stopPlayer()
            return@setOnErrorListener true
        }
        stream.setOnCompletionListener { player ->
            Log.wtf("MainActivity", "stream play completed")

            stopPlayer()
        }
    }

    private fun stopPlayer() {
        if (stream.isPlaying) {
            stream.stopPlayback()
        }
    }
}