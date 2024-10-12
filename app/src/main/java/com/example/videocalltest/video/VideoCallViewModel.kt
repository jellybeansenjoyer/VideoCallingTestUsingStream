package com.example.videocalltest.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.launch

class VideoCallViewModel(private val videoClient: StreamVideo) : ViewModel() {

    var state by mutableStateOf(VideoCallState(call=videoClient.call(
        id = "main-room",
        type="default"
    )))
    private set

    fun onAction(action: VideoCallAction) {
        when (action) {
            VideoCallAction.JoinCall -> {
                joinCall()
            }
            VideoCallAction.onDisconnectCall -> {
                state.call.leave()
                videoClient.logOut()
                state = state.copy(callState = CallState.ENDED)
            }
        }
    }

     fun joinCall(){
        if(state.callState==CallState.CONNECTED){
            return
        }
        viewModelScope.launch {
            state = state.copy(callState = CallState.JOINING)
            val shouldCreate = videoClient.queryCalls(filters = emptyMap()).getOrNull()?.calls?.isEmpty() == true

            state.call.join(create = shouldCreate)
                .onSuccess {
                    state = state.copy(callState = CallState.CONNECTED, errorMessage = null)
                }
                .onError {
                    state = state.copy(callState = null, errorMessage = it.message)
                }
        }
    }
}