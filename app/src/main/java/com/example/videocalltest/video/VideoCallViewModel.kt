package com.example.videocalltest.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.getstream.video.android.core.StreamVideo

class VideoCallViewModel(private val videoClient: StreamVideo) : ViewModel() {

    var state by mutableStateOf(VideoCallState(call=videoClient.call(
        id = "main-room",
        type="default"
    )))
    private set

    fun onAction(action: VideoCallAction) {
        when (action) {
            VideoCallAction.JoinCall -> {

            }
            VideoCallAction.onDisconnectCall -> {

            }
        }
    }
}