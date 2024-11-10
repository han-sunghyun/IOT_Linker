package com.developsunghyun.iot_linker.View

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget
import com.developsunghyun.iot_linker.View.Widget.Test
import com.developsunghyun.iot_linker.ViewModel.Test1

enum class WidgetList {
    BUTTON,
    CHECKBOX,
    SWITCH,
}

@Composable
fun GetComposableFun(viewModel: Test1 = viewModel()) {
    when(viewModel.selectedScreen){
        WidgetList.BUTTON -> ButtonWidget()
        WidgetList.SWITCH -> Test()
        else -> ButtonWidget()
    }
}