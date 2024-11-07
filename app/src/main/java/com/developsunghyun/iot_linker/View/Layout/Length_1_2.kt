package com.developsunghyun.iot_linker.View.Layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.developsunghyun.iot_linker.View.Widget.ButtonWidget

@Preview
@Composable
fun Test(
    widget: @Composable () -> Unit = { ButtonWidget() }
){
    widget()
}

