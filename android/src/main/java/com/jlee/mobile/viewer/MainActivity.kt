package com.jlee.mobile.viewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    /*
     * Throws QCMediaPlayer not present - samsung
     * val RTSP_STREAM = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov"
     */
    val HLS_STREAM = "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"

    lateinit var stream: VideoView
    lateinit var controller: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stream = findViewById(R.id.vv_stream_viewer) as VideoView

        initializeVideoView()
    }

    private fun initializeVideoView() {
        controller = MediaController(this)
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

    override fun onResume() {
        super.onResume()

        if (stream.isPlaying) {
            return
        }

        stream.start()
    }

    override fun onPause() {
        super.onPause()

        stream.stopPlayback();
    }

    private fun stopPlayer() {
        if (stream.isPlaying) {
            stream.stopPlayback()
        }
    }
}
