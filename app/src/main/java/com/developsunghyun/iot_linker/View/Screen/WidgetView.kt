package com.developsunghyun.iot_linker.View.Screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.SwitchWidget
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetView(
    bluetoothViewModel: BluetoothControlViewModel,
    database: LocalDataRepository,
    widgetType: String = "",
    name: String = "",
    image: String = "",
    intValue: Int,

){
    val widgetViewList by database.getData(intValue).observeAsState() // LiveData를 관찰

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = widgetViewList?.name ?: "")
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center
            )
            {
                when(widgetViewList?.widgetType){
                    "ButtonWidget" -> {
                        ButtonWidget(
                            modifier = Modifier,
                            bluetoothViewModel = bluetoothViewModel,
                            labelStrList = widgetViewList!!.setStrData1,
                            positionStrList = widgetViewList!!.setStrData2,
                            writeDataStrEnableList1 = widgetViewList!!.writeData1Enable,
                            writeDataStrList1 = widgetViewList!!.writeData1,
                            writeDataStrEnableList2 = widgetViewList!!.writeData2Enable,
                            writeDataStrList2 = widgetViewList!!.writeData2,
                        )
                    }
                    "SwitchWidget" -> {
                        SwitchWidget(
                            modifier = Modifier,
                            bluetoothViewModel = bluetoothViewModel,
                            labelStrList = widgetViewList!!.setStrData1,
                            writeDataStrList1 = widgetViewList!!.writeData1,
                            writeDataStrList2 = widgetViewList!!.writeData2,
                            readDataStrList1 = widgetViewList!!.readData1,
                            readDataStrList2 = widgetViewList!!.readData2,
                        )
                    }
                }
            }
        },
        bottomBar = {

        }
    )
}