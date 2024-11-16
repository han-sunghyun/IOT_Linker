package com.developsunghyun.iot_linker.View.Widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ButtonWidget(
    labelStrList: String? = "Button1,a,v,3.14",
    positionStrList: String = "0,1,2,1"
){

    val labelList = if (labelStrList != null) {
        labelStrList.split(",").toMutableList()
    }else{
        mutableListOf()
    }
    val positionList = if (positionStrList != null){
        positionStrList.split(",").toMutableList()
    }else{
        mutableListOf()
    }

    Surface(
        modifier = Modifier
    ) {
        LazyColumn {
            items(labelList.size){ index ->
                ButtonCell(label = labelList[index], position = positionList[index].toInt())
            }
        }

    }
}

@Preview
@Composable
fun ButtonCell(
    label: String = "Button1",
    position: Int = 0
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = label,
                textAlign = when(position){
                    0 -> {TextAlign.Start}
                    1 -> {TextAlign.Center}
                    2 -> {TextAlign.End}
                    else -> {TextAlign.Center}
                }
                )
        }
    }
}