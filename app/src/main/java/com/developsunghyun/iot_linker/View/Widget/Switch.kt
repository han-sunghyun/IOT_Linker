package com.developsunghyun.iot_linker.View.Widget

import android.annotation.SuppressLint
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developsunghyun.iot_linker.Model.Repository.LocalDataRepository
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.View.Components.WidgetSettingsView
import com.developsunghyun.iot_linker.View.Screen.bitmapToByteArray
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel
import com.developsunghyun.iot_linker.ViewModel.WidgetViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SwitchWidgetScreen(
    database: LocalDataRepository,
    bluetoothViewModel: BluetoothControlViewModel
){
    val viewModel: WidgetViewModel = viewModel()

    val setStrData1 = viewModel.setStrData1.collectAsState()

    val writeData1 = viewModel.writeData1.collectAsState()
    val writeData2 = viewModel.writeData2.collectAsState()

    val readData1 = viewModel.readData1.collectAsState()
    val readData2 = viewModel.readData2.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()
    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("버튼",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                actions = {
                    IconButton(
                        onClick = {
                            //LayoutBitmapCapture()
                            coroutineScope.launch {
                                bitmap = graphicsLayer.toImageBitmap()
                                val bitmapByteArray = bitmapToByteArray(bitmap!!.asAndroidBitmap())
                                Log.d("bitmap", bitmapByteArray.size.toString())

                                database.widgetDataInsert(
                                    name = "SwitchWidget_Name",
                                    widgetType = "SwitchWidget",
                                    image = Base64.encodeToString(bitmapByteArray, Base64.DEFAULT),
                                    setStrData1 = setStrData1.value!!,
                                    writeData1 = writeData1.value!!,
                                    writeData2 = writeData2.value!!,
                                    readData1 = readData1.value!!,
                                    readData2 = readData2.value!!
                                )
                            }

                        }
                    ) {

                    }
                }
            )
        },
        content = {paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues))
            {
                SwitchWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .drawWithContent {
                            // call record to capture the content in the graphics layer
                            graphicsLayer.record {
                                // draw the contents of the composable into the graphics layer
                                this@drawWithContent.drawContent()
                            }
                            // draw the graphics layer on the visible canvas
                            drawLayer(graphicsLayer)
                        },
                    bluetoothViewModel = bluetoothViewModel,
                    labelStrList = setStrData1.value.toString(),

                    writeDataStrList1 = writeData1.value.toString(),
                    writeDataStrList2 = writeData2.value.toString(),
                    readDataStrList1 = readData1.value.toString(),
                    readDataStrList2 = readData2.value.toString(),
                )
                SwitchWidgetSettings(
                    modifier = Modifier
                        .weight(1f),
                    viewModel
                )
            }
        }
    )
}

@Composable
fun SwitchWidget(
    modifier: Modifier = Modifier,
    bluetoothViewModel: BluetoothControlViewModel = viewModel(),
    labelStrList: String? = "Switch1,a,v,3.14",
    writeDataStrList1: String? = "false,true,false,true",
    writeDataStrList2: String? = "false,true,false,true",
    readDataStrList1: String? = "a1,a2,a3,a4",
    readDataStrList2: String? = "b1,b2,b3,b4",
){

    val labelList = labelStrList?.split(",")?.toMutableList() ?: mutableListOf()
    val switchOnWriteList = writeDataStrList1?.split(",")?.toMutableList() ?: mutableListOf()
    val switchOffWriteList = writeDataStrList2?.split(",")?.toMutableList() ?: mutableListOf()
    val switchOnReadList = readDataStrList1?.split(",")?.toMutableList() ?: mutableListOf()
    val switchOffReadList = readDataStrList2?.split(",")?.toMutableList() ?: mutableListOf()

    Surface(
        modifier = modifier
    ) {
        val scrollState = rememberScrollState()
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState),
            ) {
                labelList.forEachIndexed { index, s ->
                    SwitchCell(
                        bluetoothViewModel,
                        label = labelList[index],
                        onWriteData = switchOnWriteList[index].toString(),
                        offWriteData = switchOffWriteList[index].toString(),
                        switchOnRead = switchOnReadList[index],
                        switchOffRead = switchOffReadList[index],
                    )
                }
            }
        }

    }
}

@Composable
fun SwitchCell(
    bluetoothViewModel: BluetoothControlViewModel = viewModel(),
    label: String,
    onWriteData: String,
    offWriteData: String,
    switchOnRead: String,
    switchOffRead: String
){
    var switchState by remember { mutableStateOf(true) }

    val readData by bluetoothViewModel.readData.collectAsState()

    LaunchedEffect(readData) {
        switchState = when (readData) {
            switchOnRead -> true
            switchOffRead -> false
            else -> switchState // 상태를 유지
        }
    }

    Surface(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier,
                text = label,
                fontSize = 18.sp
            )

            Switch(
                modifier = Modifier,
                onCheckedChange = {
                    switchState = it
                    if(switchState) bluetoothViewModel.writeData(onWriteData)
                    else bluetoothViewModel.writeData(offWriteData)
                                  },
                checked = switchState
            )
        }
    }
}

@Preview
@Composable
fun SwitchWidgetSettings(
    modifier: Modifier = Modifier,
    viewModel: WidgetViewModel = viewModel()
){
    val thisNumber = remember { mutableIntStateOf(0) }
    val switchWidgetDataList = remember { mutableStateListOf<SwitchWidgetData>() }

    val labelStrData = remember { mutableStateListOf("") }

    val switchOnWriteData = remember { mutableStateListOf("") }
    val switchOffWriteData = remember { mutableStateListOf("") }

    val switchOnReadData = remember { mutableStateListOf("") }
    val switchOffReadData = remember { mutableStateListOf("") }

    viewModel.setStrData1(labelStrData.joinToString(",") )

    viewModel.writeData1(switchOnWriteData.joinToString(",") )
    viewModel.writeData2(switchOffWriteData.joinToString(",") )

    viewModel.readData1(switchOnReadData.joinToString(",") )
    viewModel.readData2(switchOffReadData.joinToString(",") )


    if(switchWidgetDataList.size <= 0){
        switchWidgetDataList.add(
            SwitchWidgetData("", "", "", "", "")
        )
    }

    switchWidgetDataList.forEachIndexed{ index, data ->
        labelStrData[index] = data.label
        switchOnWriteData[index] = data.switchOnWrite
        switchOffWriteData[index] = data.switchOffWrite
        switchOnReadData[index] = data.switchOnRead
        switchOffReadData[index] = data.switchOffRead
    }
    Column(
        modifier = modifier
    ) {
        LazyRow {
            items(switchWidgetDataList.size) { index ->
                TitleTag("${index + 1} 번"){
                    thisNumber.intValue = index
                }
            }
            item {
                TitleTag("+추가"){ switchWidgetDataList.add(
                    SwitchWidgetData("", "", "")
                )
                    labelStrData.add("")
                    switchOnWriteData.add("")
                    switchOffWriteData.add("")
                    switchOnReadData.add("")
                    switchOffReadData.add("")
                    thisNumber.intValue = switchWidgetDataList.size - 1
                }
            }
        }

        WidgetSettingsView(
            parametersTextList = listOf(switchWidgetDataList[thisNumber.intValue].label),
            parametersHintList = listOf("라벨"),
            parametersFieldList = listOf(){text -> switchWidgetDataList[thisNumber.intValue].label = text},

            dataWriteTextList = listOf(
                switchWidgetDataList[thisNumber.intValue].switchOnWrite,
                switchWidgetDataList[thisNumber.intValue].switchOffWrite),
            dataWriteHintList = listOf("write1", "write2"),
            dataWriteFieldList = listOf(
                {text -> switchWidgetDataList[thisNumber.intValue].switchOnWrite = text},
                {text -> switchWidgetDataList[thisNumber.intValue].switchOffWrite = text}
            ),

            guideWriteTextList = listOf("가이드", "1"),

            dataReadTextList = listOf(
                switchWidgetDataList[thisNumber.intValue].switchOnRead,
                switchWidgetDataList[thisNumber.intValue].switchOffRead,),
            dataReadHintList = listOf("read1", "read2"),
            dataReadFieldList = listOf(
                {text -> switchWidgetDataList[thisNumber.intValue].switchOnRead = text},
                {text -> switchWidgetDataList[thisNumber.intValue].switchOffRead = text},
            ),
            guideReadTextList = listOf("가이드", "1"),
        )

    }
}


class SwitchWidgetData(
    label: String = "",

    switchOnWrite: String = "",
    switchOffWrite: String = "",

    switchOnRead: String = "",
    switchOffRead: String = "",
){
    var label by mutableStateOf(label)
    var switchOnWrite by mutableStateOf(switchOnWrite)
    var switchOffWrite by mutableStateOf(switchOffWrite)
    var switchOnRead by mutableStateOf(switchOnRead)
    var switchOffRead by mutableStateOf(switchOffRead)

}