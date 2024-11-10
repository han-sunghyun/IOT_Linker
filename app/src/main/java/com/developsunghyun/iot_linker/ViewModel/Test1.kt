package com.developsunghyun.iot_linker.ViewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.WidgetList

class Test1 : ViewModel() {
    var selectedScreen by mutableStateOf<WidgetList>(WidgetList.BUTTON)
    fun selectScreen(screen: WidgetList) {
        selectedScreen = screen
    }
}