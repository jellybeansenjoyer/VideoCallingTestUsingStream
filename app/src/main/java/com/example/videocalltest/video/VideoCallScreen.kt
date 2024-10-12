package com.example.videocalltest.video

import android.os.Build
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.Manifest
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import io.getstream.video.android.compose.permission.rememberCallPermissionsState
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent

@Composable
fun VideoCallScreen(state: VideoCallState,onAction: (VideoCallAction)->Unit) {
    when {
        state.errorMessage!=null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        state.callState==CallState.JOINING -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Joining...")
            }
        }

        else -> {
            val basePermissions = listOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
            val bluetoothPermissions = if(Build.VERSION.SDK_INT >= 31) { listOf(Manifest.permission.BLUETOOTH_CONNECT) } else { emptyList<String>() }
            val notifivationPermissions = if(Build.VERSION.SDK_INT >= 33) { listOf(Manifest.permission.POST_NOTIFICATIONS) } else { emptyList<String>() }
            val context= LocalContext.current
            CallContent(
                call = state.call,
                modifier = Modifier.fillMaxSize(),
                permissions = rememberCallPermissionsState(
                    call = state.call,
                    permissions = basePermissions + bluetoothPermissions + notifivationPermissions,
                    onPermissionsResult = {permissions->
                        if(permissions.values.contains(false)){
                            Toast.makeText(context,"Not all permissions granted",Toast.LENGTH_SHORT).show()
                        }else{
                            onAction(VideoCallAction.JoinCall)
                        }
                    }
                ),
            )

        }
    }
}