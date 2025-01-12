package com.developsunghyun.iot_linker.View.Screen.ContentScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.Components.ItemLayout
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.developsunghyun.iot_linker.Model.Data.WidgetData

//@Preview(showBackground = true, widthDp = 370, heightDp = 740)
@Composable
fun ModuleScreen(
    navController : NavController,
    windowSizeClass: WindowWidthSizeClass,
    database: LocalDataRepository,
){
    val widgetViewList by database.getListData().observeAsState(emptyList()) // LiveData를 관찰
    Box(
        modifier = Modifier
            .padding(6.dp),
    ){
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 110.dp)
        ) {

            items(widgetViewList) { widgetData -> // 데이터 객체를 변수명 'widgetData'로 사용
                ItemLayout(image = base64ToBitmap(widgetData.image), label = widgetData.name) {
                    navController.navigate("WidgetView/${widgetData.id}")
                } // ItemLayout2에 'widgetData' 전달
            }
        }


    }
}
fun base64ToBitmap(base64String: String): ImageBitmap {
    val byteArray = Base64.decode(base64String, Base64.DEFAULT) // Base64 문자열을 ByteArray로 디코딩
    return byteArrayToImageBitmap(byteArray)
}
fun byteArrayToImageBitmap(byteArray: ByteArray): androidx.compose.ui.graphics.ImageBitmap {
    // ByteArray를 Bitmap으로 변환
    val bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    // Bitmap을 ImageBitmap으로 변환
    return bitmap.asImageBitmap()
}