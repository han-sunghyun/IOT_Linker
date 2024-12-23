package com.developsunghyun.iot_linker.View.Components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.developsunghyun.iot_linker.Model.Data.BluetoothDeviceData
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MinimalDialog(
    viewModel: BluetoothControlViewModel,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                for (deviceData in viewModel.pairedDevices.value){
                    Text(
                        modifier = Modifier
                            .clickable { viewModel.setDeviceData(deviceData.deviceName, deviceData.deviceAddress)  },
                        text = deviceData.deviceName
                    )
                }
            }
        }
    }
}