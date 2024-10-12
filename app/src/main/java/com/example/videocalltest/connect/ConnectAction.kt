package com.example.videocalltest.connect

sealed interface ConnectAction {
    data class OnNameChange(val name: String) : ConnectAction
    data object OnConnectClick : ConnectAction
}