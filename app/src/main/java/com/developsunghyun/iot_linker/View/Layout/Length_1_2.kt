package com.developsunghyun.iot_linker.View.Layout

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developsunghyun.iot_linker.View.GetComposableFun
import com.developsunghyun.iot_linker.View.WidgetList
import com.developsunghyun.iot_linker.ViewModel.Test1

@Preview
@Composable
fun MainTest(
    viewModel: Test1 = viewModel()

){
    Column {
        GetComposableFun(viewModel)
        Button(
            onClick = {viewModel.selectScreen(WidgetList.SWITCH)}
        ) {
            Text("버튼")
        }
    }
}

