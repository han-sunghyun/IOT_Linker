package com.developsunghyun.iot_linker.View.Screen.ContentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developsunghyun.iot_linker.View.Components.ItemLayout2

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun ToolScreen(){
    Box(
        modifier = Modifier
            .padding(6.dp),
    ){
        Column (

        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ItemLayout2(modifier = Modifier.weight(1f))
                ItemLayout2(modifier = Modifier.weight(1f))
                ItemLayout2(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ItemLayout2(modifier = Modifier.weight(1f))
                ItemLayout2(modifier = Modifier.weight(1f))
                ItemLayout2(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ItemLayout2(modifier = Modifier.weight(1f))
                ItemLayout2(modifier = Modifier.weight(1f))
                ItemLayout2(modifier = Modifier.weight(1f))
            }

        }

    }
}