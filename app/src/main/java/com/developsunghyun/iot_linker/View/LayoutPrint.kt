package com.developsunghyun.iot_linker.View

import androidx.compose.runtime.Composable
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_1
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_2
import com.developsunghyun.iot_linker.View.Layout.Layout_2Slot_3
import com.developsunghyun.iot_linker.View.Layout.Layout_3Slot_1
import com.developsunghyun.iot_linker.View.Layout.Layout_3Slot_2
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.SwitchWidget
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel

class LayoutPrint(
    bluetoothViewModel: BluetoothControlViewModel,
    var layout: String,
    var module: MutableList<String>,
    var setStrData1: MutableList<String>,//0 ~ 5
    var setStrData2: MutableList<String>,//0 ~ 5
) {
    private var widgetList: List<Map<String, @Composable () -> Unit>> = List(6) { index ->
        mapOf(
            "ButtonWidget" to { ButtonWidget(bluetoothViewModel = bluetoothViewModel, labelStrList = setStrData1.joinToString(",")) },
            "SwitchWidget" to { SwitchWidget(bluetoothViewModel = bluetoothViewModel, labelStrList = setStrData1.joinToString(",")) }
        )
    }

    private var layout2Slot: Map<String,  @Composable (@Composable () -> Unit) -> Unit> = mapOf(
        "Layout_2Slot_1" to
                { Layout_2Slot_1( { widgetList[0][module[0]]?.invoke() }, { widgetList[1][module[1]]?.invoke() }) },
        "Layout_2Slot_2" to
                { Layout_2Slot_2( { widgetList[0][module[0]]?.invoke() }, { widgetList[1][module[1]]?.invoke() }) },
        "Layout_2Slot_3" to
                { Layout_2Slot_3( { widgetList[0][module[0]]?.invoke() }, { widgetList[1][module[1]]?.invoke() }) },

        "Layout_3Slot_1" to
                { Layout_3Slot_1( { widgetList[0][module[0]]?.invoke() }, { widgetList[1][module[1]]?.invoke() }, { widgetList[2][module[2]]?.invoke() }) },
        "Layout_3Slot_2" to
                { Layout_3Slot_2( { widgetList[0][module[0]]?.invoke() }, { widgetList[1][module[1]]?.invoke() }, { widgetList[2][module[2]]?.invoke() }) },
    )

    @Composable
    fun PreView(){
        layout2Slot[layout]?.invoke {
            layout2Slot[layout] // 동적으로 `widget` 호출
        }
    }
}