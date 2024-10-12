package com.example.videocalltest.connect

data class ConnectState(
    val name:String = "",
    val isConnected:Boolean = false,
    val error:String? = null
)
