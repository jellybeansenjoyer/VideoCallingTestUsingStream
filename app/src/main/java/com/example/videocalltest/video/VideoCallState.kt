package com.example.videocalltest.video

import io.getstream.video.android.core.Call

data class VideoCallState(
    val call: Call,
    val callState: CallState? = null,
    val errorMessage: String? = null
    )

enum class CallState {
    JOINING,
    CONNECTED,
    ENDED
}