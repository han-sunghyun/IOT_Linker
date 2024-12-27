package com.developsunghyun.iot_linker.Model.Data

import androidx.compose.runtime.Composable
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget


class WidgetsData(
    var screenName: String,
    var label: String,
    var image: Int,
)

val widgetDataList: MutableList<WidgetsData> = mutableListOf(
    WidgetsData("ButtonWidgetScreen", "버튼", R.drawable.button),
    WidgetsData("ButtonWidgetScreen", "스위치", R.drawable.widget_switch),
    WidgetsData("ButtonWidgetScreen", "슬라이드 바", R.drawable.slidebar),
    WidgetsData("ButtonWidget", "패드", R.drawable.pad),
    WidgetsData("ButtonWidget", "텍스트", R.drawable.text),
    WidgetsData("ButtonWidget", "조이스틱", R.drawable.joystick),
    WidgetsData("ButtonWidget", "터치패드", R.drawable.touchpad),
    WidgetsData("ButtonWidget", "자이로센서", R.drawable.gyro),
    WidgetsData("ButtonWidget", "키보드", R.drawable.keyboard),
    WidgetsData("ButtonWidget", "파형", R.drawable.waveform),
    WidgetsData("ButtonWidget", "디스플레이", R.drawable.display),
)

