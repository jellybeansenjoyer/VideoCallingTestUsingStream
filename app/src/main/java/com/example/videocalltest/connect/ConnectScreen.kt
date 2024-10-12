package com.example.videocalltest.connect

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.videocalltest.ui.theme.VideoCallTestTheme
import org.koin.core.qualifier.named
import org.openapitools.client.models.ConnectedEvent

@Composable
fun ConnectScreen(state: ConnectState,
                  onAction: (ConnectAction) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text(
           text = "Choose a name",
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
       TextField(value=state.name, onValueChange = {
           onAction(ConnectAction.OnNameChange(it))
       }, placeholder = {
           Text(text = "Enter a name")
       },
           modifier = Modifier.fillMaxWidth())
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(
            onClick = {
                onAction(ConnectAction.OnConnectClick)
            },
            modifier = Modifier.align(alignment = Alignment.End)
        ){
            Text(text = "Connect")
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        if(state.error!=null)
        {
            Text(text = state.error, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ConnectScreenPreview() {
    VideoCallTestTheme {
        ConnectScreen(state = ConnectState(error = "Wrong"), onAction={})
    }
}