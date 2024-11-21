package com.developsunghyun.iot_linker.View.Layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developsunghyun.iot_linker.View.Components.NoneModule
import com.developsunghyun.iot_linker.View.Components.SlotBox
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_2Slot_1(
    module1: @Composable () -> Unit = { NoneModule() },
    module2: @Composable () -> Unit = { NoneModule() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module1, slotNumber = 0)
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module2, slotNumber = 1)
        }
    }
}

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_2Slot_2(
    module1: @Composable () -> Unit = { NoneModule() },
    module2: @Composable () -> Unit = { NoneModule() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module1, slotNumber = 0)
        }
        Box(
            modifier = Modifier
                .weight(2f)
        ){
            SlotBox(module = module2, slotNumber = 1)
        }
    }
}

@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_2Slot_3(
    module1: @Composable () -> Unit = { NoneModule() },
    module2: @Composable () -> Unit = { NoneModule() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
    ) {
        Box(
            modifier = Modifier
                .weight(2f)
        ){
            SlotBox(module = module1, slotNumber = 0)
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module2, slotNumber = 1)
        }
    }
}