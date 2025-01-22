package com.developsunghyun.iot_linker.View.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developsunghyun.iot_linker.Model.Data.ModuleData
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.View.Screen.ContentScreen.base64ToBitmap
import com.developsunghyun.iot_linker.ViewModel.CreateViewModel

@Preview
@Composable
fun SlotBox(
    database: LocalDataRepository? = null,
    module: @Composable () -> Unit = {  },
    slotNumber: Int = 0,
    viewModel: CreateViewModel = viewModel(LocalContext.current as ViewModelStoreOwner),
){

    var dialogSettingsView by remember { mutableStateOf(false) }
    var dialogModuleView by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground)
            .fillMaxSize()
            .padding(3.dp),
        shape = RoundedCornerShape(7.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ){
            module()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.TopEnd,
        ) {

            SmallFloatingActionButton(
                onClick = {
                    dialogSettingsView = true
                },
            ) {
                Icon(Icons.Filled.Create, "Floating action button.")
            }
        }
    }

    if(dialogSettingsView){
        val itemList = listOf("모듈 가져오기", "새로 만들기", "이동", "제거 하기")
        Dialog(onDismissRequest = { dialogSettingsView = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Column {
                    itemList.forEachIndexed{index, item ->
                        Text(
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    when(index){
                                        0 -> dialogModuleView = true
                                    }
                                },
                            text = item,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
    if(dialogModuleView){
        dialogSettingsView = false
        val widgetViewList by database!!.getListData().observeAsState(emptyList()) // LiveData를 관찰

        Dialog(onDismissRequest = { dialogModuleView = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Column {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 110.dp)
                    ) {

                        items(widgetViewList) { widgetData -> // 데이터 객체를 변수명 'widgetData'로 사용
                            ItemLayout(image = base64ToBitmap(widgetData.image), label = widgetData.name) {
                                viewModel.updateModule(index = slotNumber,
                                    newModuleData = ModuleData(
                                        widgetType = widgetData.widgetType,
                                        setStrData1 = widgetData.setStrData1,
                                        setStrData2 = widgetData.setStrData2,
                                        setStrData3 = widgetData.setStrData3,
                                        setStrData4 = widgetData.setStrData4,
                                        writeData1 = widgetData.writeData1,
                                        writeData1Enable = widgetData.writeData1Enable,
                                        writeData2 = widgetData.writeData2,
                                        writeData2Enable = widgetData.writeData2Enable,
                                        writeData3 = widgetData.writeData3,
                                        writeData3Enable = widgetData.writeData3Enable,
                                        writeData4 = widgetData.writeData4,
                                        writeData4Enable = widgetData.writeData4Enable,
                                        readData1 = widgetData.readData1,
                                        readData2 = widgetData.readData2,
                                        readData3 = widgetData.readData3,
                                        readData4 = widgetData.readData4,

                                        )
                                )

                                Log.d("TEST", viewModel.modules.value[slotNumber].widgetType)
                            } // ItemLayout2에 'widgetData' 전달
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NoneModule(

    text: String = ""
){
    Box(
        modifier = Modifier
            .background(color = Color(0xFF313131))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.background)
    }
}