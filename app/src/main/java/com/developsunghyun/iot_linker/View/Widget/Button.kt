package com.developsunghyun.iot_linker.View.Widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
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
import android.graphics.Bitmap
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ButtonWidgetScreen(
    database: LocalDataRepository,
    bluetoothViewModel: BluetoothControlViewModel
){
    val viewModel: WidgetViewModel = viewModel()

    val setStrData1 = viewModel.setStrData1.collectAsState()
    val setStrData2 = viewModel.setStrData2.collectAsState()

    val writeData1Enable = viewModel.writeData1Enable.collectAsState()
    val writeData1 = viewModel.writeData1.collectAsState()

    val writeData2Enable = viewModel.writeData2Enable.collectAsState()
    val writeData2 = viewModel.writeData2.collectAsState()

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
                                // 기존 비트맵에서 해상도 낮추기
                                bitmap = graphicsLayer.toImageBitmap()
                                val scaledBitmap = Bitmap.createScaledBitmap(
                                    bitmap!!.asAndroidBitmap(),256, 256, true)
                                // 리사이즈된 비트맵을 바이트 배열로 변환
                                val bitmapByteArray = bitmapToByteArray(scaledBitmap)

//                                bitmap = graphicsLayer.toImageBitmap()
//                                val bitmapByteArray = bitmapToByteArray(bitmap!!.asAndroidBitmap())

                                database.widgetDataInsert(
                                    name = "testName",
                                    widgetType = "ButtonWidget",
                                    image = Base64.encodeToString(bitmapByteArray, Base64.DEFAULT),
                                    setStrData1 = setStrData1.value!!,
                                    setStrData2 = setStrData2.value!!,
                                    writeData1Enable = writeData1Enable.value,
                                    writeData1 = writeData1.value!!,
                                    writeData2Enable = writeData2Enable.value,
                                    writeData2 = writeData2.value!!,
                                )
                            }

                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.save),
                            contentDescription = ""
                        )
                    }
                }
            )
        },
        content = {paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues))
            {
                ButtonWidget(
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
                    positionStrList = setStrData2.value.toString(),
                    writeDataStrEnableList1 = writeData1Enable.value,
                    writeDataStrList1 = writeData1.value.toString(),
                    writeDataStrEnableList2 = writeData2Enable.value,
                    writeDataStrList2 = writeData2.value.toString()
                )
                ButtonWidgetSettings(
                    modifier = Modifier
                        .weight(1f),
                    viewModel
                )
            }
        }
    )
}

@Composable
fun ButtonWidget(
    modifier: Modifier = Modifier,
    bluetoothViewModel: BluetoothControlViewModel = viewModel(),
    labelStrList: String? = "Button1,a,v,3.14",
    positionStrList: String? = "0,1,2,1",

    writeDataStrEnableList1: String? = null,
    writeDataStrList1: String? = "",

    writeDataStrEnableList2: String? = null,
    writeDataStrList2: String? = ""
){

    val labelList = labelStrList?.split(",")?.toMutableList() ?: mutableListOf()
    val positionList = positionStrList?.split(",")?.toMutableList() ?: mutableListOf()

    val writeDataEnableList1 = writeDataStrEnableList1?.split(",")?.toMutableList() ?: mutableListOf()
    val writeDataList1 = writeDataStrList1?.split(",")?.toMutableList() ?: mutableListOf()

    val writeDataEnableList2 = writeDataStrEnableList2?.split(",")?.toMutableList() ?: mutableListOf()
    val writeDataList2 = writeDataStrList2?.split(",")?.toMutableList() ?: mutableListOf()

    Surface(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ){
            LazyColumn {
                items(labelList.size){ index ->
                    ButtonCell(
                        bluetoothViewModel,
                        label = labelList[index],
                        position = positionList[index].toIntOrNull() ?: 0,
                        writeDataEnableList1[index],
                        writeDataList1[index],
                        writeDataEnableList2[index],
                        writeDataList2[index]
                    )
                }
            }
        }

    }
}

@Composable
fun ButtonCell(
    bluetoothViewModel: BluetoothControlViewModel = viewModel(),
    label: String = "",
    position: Int = 0,
    onPressedEnable: String = "true",
    onPressed: String = "",
    onReleasedEnable: String = "true",
    onReleased: String = ""
) {
    var isPressed by remember { mutableStateOf(false) }

    var pushEnable by remember { mutableStateOf("") }
    var push by remember { mutableStateOf("") }

    var upEnable by remember { mutableStateOf("") }
    var up by remember { mutableStateOf("") }

    pushEnable = onPressedEnable
    push = onPressed

    upEnable = onReleasedEnable
    up = onReleased

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        shape = RoundedCornerShape(30.dp),
        color = if (isPressed) MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
        else MaterialTheme.colorScheme.primary
    ) {
        Box(
            modifier = Modifier
                .padding(7.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            try {
                                if(pushEnable.toBoolean()) bluetoothViewModel.writeData(push)
                                tryAwaitRelease()
                                if(upEnable.toBoolean()) bluetoothViewModel.writeData(up)
                            } catch (e: Exception) {
                                Log.e("LOG", "Error during writeData: $e")
                            } finally {
                                isPressed = false
                            }
                        }
                    )
                }
        ) {
            val textAlign = mapOf(
                0 to TextAlign.Start,
                1 to TextAlign.Center,
                2 to TextAlign.End
            ).getOrDefault(position, TextAlign.Center)

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = label,
                fontSize = 20.sp,
                textAlign = textAlign
            )
        }
    }
}


@Preview
@Composable
fun ButtonWidgetSettings(
    modifier: Modifier = Modifier,
    viewModel: WidgetViewModel = viewModel()
){
    val thisNumber = remember { mutableIntStateOf(0) }
    val buttonWidgetDataList = remember { mutableStateListOf<ButtonWidgetData>() }

    val labelStrData = remember { mutableStateListOf("") }
    val positionStrData = remember { mutableStateListOf("") }

    val pushEnableData = remember { mutableStateListOf("true") }
    val pushData = remember { mutableStateListOf("") }

    val upEnableData = remember { mutableStateListOf("true") }
    val upData = remember { mutableStateListOf("") }

    viewModel.setStrData1(labelStrData.joinToString(",") )
    viewModel.setStrData2(positionStrData.joinToString(",") )

    viewModel.writeData1Enable(pushEnableData.joinToString(","))
    viewModel.writeData1(pushData.joinToString(",") )

    viewModel.writeData2Enable(upEnableData.joinToString(","))
    viewModel.writeData2(upData.joinToString(",") )

    if(buttonWidgetDataList.size <= 0){
        buttonWidgetDataList.add(
            ButtonWidgetData("", "0", "true", "", "true", "")
        )
    }

    buttonWidgetDataList.forEachIndexed{ index, data ->
        labelStrData[index] = data.label
        positionStrData[index] = data.position
        pushEnableData[index] = data.pushEnable
        pushData[index] = data.push
        upEnableData[index] = data.upEnable
        upData[index] = data.up
    }
    Column(
        modifier = modifier
    ) {
        LazyRow {
            items(buttonWidgetDataList.size) { index ->
                TitleTag("${index + 1} 번"){
                    thisNumber.intValue = index
                }
            }
            item {
                TitleTag("+추가"){ buttonWidgetDataList.add(
                    ButtonWidgetData("", "0", "true", "", "true", "")
                )
                    labelStrData.add("")
                    positionStrData.add("")
                    pushEnableData.add("true")
                    pushData.add("")
                    upEnableData.add("true")
                    upData.add("")
                    thisNumber.intValue = buttonWidgetDataList.size - 1
                    Log.d("num" ,"${buttonWidgetDataList.size}")
                }
            }
        }

        WidgetSettingsView(
            parametersTextList = listOf(buttonWidgetDataList[thisNumber.intValue].label),
            parametersHintList = listOf("라벨"),
            parametersFieldList = listOf(){text -> buttonWidgetDataList[thisNumber.intValue].label = text},

            imageButtonIconList = listOf(R.drawable.text_left, R.drawable.text_center, R.drawable.text_right),
            imageButtonEnableList = listOf(
                buttonWidgetDataList[thisNumber.intValue].position == "0",
                buttonWidgetDataList[thisNumber.intValue].position == "1",
                buttonWidgetDataList[thisNumber.intValue].position == "2"),
            imageButtonClickList = listOf(
                {buttonWidgetDataList[thisNumber.intValue].position = "0"},
                {buttonWidgetDataList[thisNumber.intValue].position = "1"},
                {buttonWidgetDataList[thisNumber.intValue].position = "2"}
            ),

            dataWriteTextList = listOf(buttonWidgetDataList[thisNumber.intValue].push,
                buttonWidgetDataList[thisNumber.intValue].up),
            dataWriteHintList = listOf("write1", "write2"),
            dataWriteFieldList = listOf(
                {text -> buttonWidgetDataList[thisNumber.intValue].push = text},
                {text -> buttonWidgetDataList[thisNumber.intValue].up = text}
            ),
            dataWriteCheckList = listOf(
                buttonWidgetDataList[thisNumber.intValue].pushEnable.toBoolean(),
                buttonWidgetDataList[thisNumber.intValue].upEnable.toBoolean()),
            dataWriteCheckBoxList = listOf(
                {
                    if(buttonWidgetDataList[thisNumber.intValue].pushEnable.toBoolean()){
                        buttonWidgetDataList[thisNumber.intValue].pushEnable = "false"
                    }else{
                        buttonWidgetDataList[thisNumber.intValue].pushEnable = "true"
                    }
                },
                {
                    if(buttonWidgetDataList[thisNumber.intValue].upEnable.toBoolean()){
                        buttonWidgetDataList[thisNumber.intValue].upEnable = "false"
                    }else{
                        buttonWidgetDataList[thisNumber.intValue].upEnable = "true"
                    }
                }
            ),
            guideWriteTextList = listOf("가이드", "1"),
        )

    }
}

@Composable
fun TitleTag(
    text: String = "text",
    click: () -> Unit = {}
){
    Surface(
        modifier = Modifier
            .padding(3.dp),
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 3.dp
    ) {
        Text(
            modifier = Modifier
                .width(70.dp)
                .padding(3.dp)
                .clickable {click()},
            text = text
        )
    }
}


class ButtonWidgetData(
    label: String = "",
    position: String = "0",

    pushEnable: String = "true",
    push: String = "",
    upEnable: String = "true",
    up: String = "",
){
    var label by mutableStateOf(label)
    var position by mutableStateOf(position)

    var pushEnable by mutableStateOf(pushEnable)
    var push by mutableStateOf(push)

    var upEnable by mutableStateOf(upEnable)
    var up by mutableStateOf(up)
}