package com.developsunghyun.iot_linker.View.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun LayoutSelect(){
    Surface(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Column(

        ) {
            SelectButton()
        }
    }
}

@Preview
@Composable
fun SelectButton(
    button1: Boolean = true,
    button2: Boolean = false,
    button1Event : () -> Unit = {},
    button2Event : () -> Unit = {},
){
    Surface(
        modifier = Modifier
            .wrapContentSize(),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHighest
    ) {
        Row (
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp),
        ){
            Text(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .clickable {
                        button1Event()
                    },
                text = "버튼1",
                fontSize = 18.sp,
                color = if(button1) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
            Text(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        button2Event()
                    },
                text = "버튼2",
                fontSize = 18.sp,
                color = if(button2) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.background
            )
        }
    }
}