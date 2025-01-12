package com.developsunghyun.iot_linker.View.Screen.ContentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.Model.Data.widgetDataList
import com.developsunghyun.iot_linker.View.Components.ItemLayout2

@Composable
fun ToolScreen(
    navController : NavController,
    windowSizeClass: WindowWidthSizeClass,
){

    Box(
        modifier = Modifier
            .padding(6.dp),
    ){
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 110.dp)
        ) {
            items(widgetDataList) { widgetData -> // 데이터 객체를 변수명 'widgetData'로 사용
                ItemLayout2(image =  widgetData.image, label = widgetData.label) {
                    navController.navigate(widgetData.screenName)
                } // ItemLayout2에 'widgetData' 전달
            }
        }


    }
}
