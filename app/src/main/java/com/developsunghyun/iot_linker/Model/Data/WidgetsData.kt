package com.developsunghyun.iot_linker.Model.Data

import androidx.compose.runtime.Composable
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget


class WidgetsData(
    var label: String,
    var image: Int,
    var widgetComposable: @Composable () -> Unit
)

val widgetDataList: MutableList<WidgetsData> = mutableListOf(
    WidgetsData("버튼", R.drawable.button) { ButtonWidget() },
    WidgetsData("스위치", R.drawable.widget_switch) { ButtonWidget() },
    WidgetsData("슬라이드 바", R.drawable.slidebar) { ButtonWidget() },
    WidgetsData("패드", R.drawable.pad) { ButtonWidget() },
    WidgetsData("텍스트", R.drawable.text) { ButtonWidget() },
    WidgetsData("조이스틱", R.drawable.joystick) { ButtonWidget() },
    WidgetsData("터치패드", R.drawable.touchpad) { ButtonWidget() },
    WidgetsData("자이로센서", R.drawable.gyro) { ButtonWidget() },
    WidgetsData("키보드", R.drawable.keyboard) { ButtonWidget() },
    WidgetsData("파형", R.drawable.waveform) { ButtonWidget() },
    WidgetsData("디스플레이", R.drawable.display) { ButtonWidget() },
)

