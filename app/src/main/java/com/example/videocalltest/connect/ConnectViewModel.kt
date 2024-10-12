package com.example.videocalltest.connect

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.videocalltest.VideoCalling

class ConnectViewModel (
    private val app: Application
): AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())
        private set

    fun onAction(action: ConnectAction){
        when(action){
            ConnectAction.OnConnectClick -> {
                connectToRoom()
            }
            is ConnectAction.OnNameChange -> {
                state = state.copy(name=action.name)
            }
        }
    }
    fun connectToRoom(){
        state.copy(error = null)
        if(state.name.isBlank()){
            state.copy(error = "Username cannot be null")
        }
        (app as VideoCalling).initVideo(state.name)
        state.copy(isConnected = true)
    }
}
