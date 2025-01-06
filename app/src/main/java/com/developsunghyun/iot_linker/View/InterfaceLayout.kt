package com.developsunghyun.iot_linker.View

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_1
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_2
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_3
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.SwitchWidget
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel

class InterfaceLayout(
    private val bluetoothViewModel: BluetoothControlViewModel,
    private val layoutType: String = "Layout_2Slot_1",
    private val widgetType: List<String>? = listOf("ButtonWidget", "ButtonWidget"),
    private val setStrData1: List<String>? = null,
    private val setStrData2: List<String>? = null,
    private val setStrData3: List<String>? = null,
    private val setStrData4: List<String>? = null,

    private val writeData1Enable: List<String>? = null,
    private val writeData2Enable: List<String>? = null,
    private val writeData3Enable: List<String>? = null,
    private val writeData4Enable: List<String>? = null,

    private val writeData1: List<String>? = null,
    private val writeData2: List<String>? = null,
    private val writeData3: List<String>? = null,
    private val writeData4: List<String>? = null,

) {


    private val widget: List<Map<String, @Composable () -> Unit>> = List(widgetType?.size ?: 6){ index ->
        mapOf(
            "ButtonWidget" to {
                ButtonWidget(
                    modifier = Modifier,
                    bluetoothViewModel = bluetoothViewModel,
                    labelStrList = setStrData1?.get(index),
                    positionStrList = setStrData2?.get(index),
                    writeDataStrEnableList1 = writeData1Enable?.get(index),
                    writeDataStrList1 = writeData1?.get(index),
                    writeDataStrEnableList2 = writeData2Enable?.get(index),
                    writeDataStrList2 = writeData2?.get(index),
                )
            },
            "SwitchWidget" to {
                SwitchWidget()
            }
        )
    }

    private val slot: Map<String, @Composable () -> Unit> = mapOf(
        "Layout_2Slot_1" to {
            Layout_2Slot_1(
                module1 = { widget[0][widgetType?.get(0)]?.invoke() },
                module2 = { widget[1][widgetType?.get(1)]?.invoke() }
            )
        },
        "Layout_2Slot_2" to { Layout_2Slot_2() },
        "Layout_2Slot_3" to { Layout_2Slot_3() }
    )

    @Composable
    fun Layout() {
        slot[layoutType]?.invoke()
    }

}
