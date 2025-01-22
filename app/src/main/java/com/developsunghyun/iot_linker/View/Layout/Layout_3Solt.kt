package com.developsunghyun.iot_linker.View.Layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.Components.NoneModule
import com.developsunghyun.iot_linker.View.Components.SlotBox
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget

//@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun Layout_3Slot_1(
    database: LocalDataRepository? = null,
    module1: @Composable () -> Unit = { NoneModule() },
    module2: @Composable () -> Unit = { NoneModule() },
    module3: @Composable () -> Unit = { NoneModule() }

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
            SlotBox(module = module1, slotNumber = 0, database = database)
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module2, slotNumber = 1, database = database)
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module3, slotNumber = 2, database = database)
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 740)
@Composable
fun Layout_3Slot_2(
    database: LocalDataRepository? = null,
    module1: @Composable () -> Unit = { ButtonWidget() },
    module2: @Composable () -> Unit = { NoneModule() },
    module3: @Composable () -> Unit = { NoneModule() }

){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onBackground),
    ) {
        Row(
            modifier = Modifier
                .weight(2f)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ){
                SlotBox(module = module1, slotNumber = 0, database = database)
            }
            Box(
                modifier = Modifier
                    .weight(1f)
            ){
                SlotBox(module = module2, slotNumber = 1, database = database)
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ){
            SlotBox(module = module3, slotNumber = 2, database = database)
        }
    }
}