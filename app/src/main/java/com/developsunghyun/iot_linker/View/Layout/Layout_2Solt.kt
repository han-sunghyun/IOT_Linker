package com.developsunghyun.iot_linker.View.Layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_2Slot_1(
    module1: @Composable () -> Unit = { ButtonWidget() },
    module2: @Composable () -> Unit = { ButtonWidget() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            module1()
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            module2()
        }
    }
}

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_2Slot_2(
    module1: @Composable () -> Unit = { ButtonWidget() },
    module2: @Composable () -> Unit = { ButtonWidget() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            module1()
        }
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            module2()
        }
    }
}

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_2Slot_3(
    module1: @Composable () -> Unit = { ButtonWidget() },
    module2: @Composable () -> Unit = { ButtonWidget() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            module1()
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            module2()
        }
    }
}