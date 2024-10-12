package com.example.videocalltest

import android.app.Application
import androidx.compose.runtime.currentCompositionErrors
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType

class VideoCalling : Application() {
    private var currentName:String? = null
    var videoClient: StreamVideo?=null

    fun initVideo(name:String){
        if(videoClient==null || currentName!=name){
            StreamVideo.removeClient()
            currentName = name
            videoClient = StreamVideoBuilder(context = this, apiKey = "628kkemvpydt",user= User(
                id = name,
                name=name,
                type= UserType.Guest
            )).build()
        }

    }
}