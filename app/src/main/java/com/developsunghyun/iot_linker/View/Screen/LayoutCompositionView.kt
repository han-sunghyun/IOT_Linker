package com.developsunghyun.iot_linker.View.Screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.View.LayoutPrint
import com.developsunghyun.iot_linker.ViewModel.CreateViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutCompositionView(
    navController: NavController,
    viewModel: CreateViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {
    viewModel.setModuleArray(0, "ButtonWidget")
    viewModel.setModuleArray(1, "SwitchWidget")

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
                LayoutPrint(
                    layout = viewModel.layoutData.value.layoutType,
                    module = mutableListOf(
                        viewModel.layoutData.value.module0,
                        viewModel.layoutData.value.module1,
                        viewModel.layoutData.value.module2,
                        viewModel.layoutData.value.module3,
                        viewModel.layoutData.value.module4,
                        viewModel.layoutData.value.module5
                    ),
                    setStrData1 = mutableListOf("test1,test2,test3,test4,test5", "test6,test7,test8,test9,test10,test11,a,b,c"),
                    setStrData2 = mutableListOf("0,1,0,0,1", "true,false,true,false,false,true,false,false,true")
                ).PreView()
            }
        }
    )

}