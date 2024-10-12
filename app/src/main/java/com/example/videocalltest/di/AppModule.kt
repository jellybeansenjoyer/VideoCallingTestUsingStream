package com.example.videocalltest.di

import com.example.videocalltest.VideoCalling
import com.example.videocalltest.connect.ConnectViewModel
import com.example.videocalltest.video.VideoCallViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    factory{
        val app =androidContext().applicationContext as VideoCalling
        app.videoClient
    }

    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}

