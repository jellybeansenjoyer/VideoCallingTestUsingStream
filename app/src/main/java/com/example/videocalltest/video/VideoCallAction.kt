package com.example.videocalltest.video

import com.example.videocalltest.VideoCalling

sealed interface VideoCallAction{
    data object onDisconnectCall: VideoCallAction
    data object JoinCall:VideoCallAction
}