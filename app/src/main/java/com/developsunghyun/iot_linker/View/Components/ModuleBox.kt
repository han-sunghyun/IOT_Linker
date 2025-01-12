package com.developsunghyun.iot_linker.View.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SlotBox(
    module: @Composable () -> Unit = {  },
    slotNumber: Int = 0
){
    Surface(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground)
            .fillMaxSize()
            .padding(3.dp),
        shape = RoundedCornerShape(7.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            module()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.TopEnd,
        ) {

            SmallFloatingActionButton(
                onClick = {
                    Log.d("LOG", slotNumber.toString())
                },
            ) {
                Icon(Icons.Filled.Create, "Floating action button.")
            }
        }
    }
}

@Preview
@Composable
fun NoneModule(

    text: String = ""
){
    Box(
        modifier = Modifier
            .background(color = Color(0xFF313131))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.background)
    }
}