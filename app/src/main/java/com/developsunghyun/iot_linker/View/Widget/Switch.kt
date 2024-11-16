package com.developsunghyun.iot_linker.View.Widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SwitchWidget(
    labelStrList: String? = "Switch1,a,v,3.14",
    stateSetStrList: String? = "false,true,false,true"
){

    val labelList = if (labelStrList != null) {
        labelStrList.split(",").toMutableList()
    }else{
        mutableListOf()
    }
    val stateSetList = if (stateSetStrList != null) {
        stateSetStrList.split(",").toMutableList()
    }else{
        mutableListOf()
    }

    Surface(
        modifier = Modifier
    ) {
        LazyColumn {
            items(labelList.size){ index ->
                SwitchCell(label = labelList[index], state = stateSetList[index].toBoolean())
            }
        }

    }
}

@Preview
@Composable
fun SwitchCell(
    label: String = "Switch1",
    state: Boolean = false
){
    Surface(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier,
                text = label,
                fontSize = 18.sp
            )

            Switch(
                modifier = Modifier,
                onCheckedChange = {},
                checked = state
            )
        }
    }
}