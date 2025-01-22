package com.developsunghyun.iot_linker.View.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.InterfaceLayout
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel
import com.developsunghyun.iot_linker.ViewModel.CreateViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutCompositionView(
    bluetoothViewModel: BluetoothControlViewModel,
    navController: NavController,
    viewModel: CreateViewModel = viewModel(LocalContext.current as ViewModelStoreOwner),
    database: LocalDataRepository,
) {
    val moduleListData by viewModel.modules.collectAsState()

//    viewModel.setModuleArray(0, "ButtonWidget")
//    viewModel.setModuleArray(1, "SwitchWidget")

    Scaffold(
        topBar = {
            TopAppBar(
                {
                    TextField(
                        value = "",
                        onValueChange = {}
                    )
                }

            )

        },
        content = {
            paddingValues ->
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ){

                InterfaceLayout(
                    database,
                    bluetoothViewModel = bluetoothViewModel,
                    layoutType = viewModel.interfaceLayoutType.value,
                    widgetType = moduleListData.map { it.widgetType },
                    setStrData1 = moduleListData.map { it.setStrData1 },
                    setStrData2 = moduleListData.map { it.setStrData2 },
                    writeData1Enable = moduleListData.map { it.writeData1Enable },
                    writeData1 = moduleListData.map { it.writeData1 },
                    writeData2Enable = moduleListData.map { it.writeData2Enable },
                    writeData2 = moduleListData.map { it.writeData2 },
                    readData1 = moduleListData.map { it.readData1 },
                    readData2 = moduleListData.map { it.readData2 },

                ).Layout()
            }
        }
    )

}