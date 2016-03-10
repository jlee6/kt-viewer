package com.jlee.mobile.viewer.ui.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import com.jlee.mobile.viewer.R
import kotlinx.android.synthetic.main.fragment_stream_view.*

class StreamViewFragment : Fragment() {
    /*
     * Throws QCMediaPlayer not present - samsung
     * val RTSP_STREAM = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
     */
    val HLS_STREAM = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"
    //            = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"

    val controller: MediaController by lazy { MediaController(view.context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup,
                              savedInstance: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stream_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstance: Bundle?) {
        super.onViewCreated(view, savedInstance)

        initializeVideoView()
    }

    override fun onStart() {
        super.onStart()

        if (video_stream_viewer.isPlaying) {
            return
        }

        video_stream_viewer.start()
    }

    override fun onPause() {
        super.onPause()

        video_stream_viewer.stopPlayback();
    }

    private fun initializeVideoView() {
        video_stream_viewer.setMediaController(controller)
        video_stream_viewer.setVideoPath(HLS_STREAM)
        video_stream_viewer.setOnErrorListener { player, error, extra ->
            Log.wtf("StreamView", "Stream thrown error $error: $extra")

            stopPlayer()
            return@setOnErrorListener true
        }
        video_stream_viewer.setOnCompletionListener { player ->
            Log.wtf("MainActivity", "stream play completed")

            stopPlayer()
        }
    }

    private fun stopPlayer() {
        if (video_stream_viewer.isPlaying) {
            video_stream_viewer.stopPlayback()
        }
    }
}