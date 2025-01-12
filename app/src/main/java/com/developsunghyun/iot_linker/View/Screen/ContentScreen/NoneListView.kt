package com.developsunghyun.iot_linker.View.Screen.ContentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NoneScreen(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "구현중인 페이지 입니다.",
                fontSize = 30.sp
            )
        }
    }
}
