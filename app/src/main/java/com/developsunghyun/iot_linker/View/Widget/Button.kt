package com.developsunghyun.iot_linker.View.Widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel
import com.developsunghyun.iot_linker.ViewModel.WidgetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ButtonWidgetScreen(
    bluetoothViewModel: BluetoothControlViewModel
){
    val viewModel: WidgetViewModel = viewModel()

    val setStrData1 = viewModel.setStrData1.collectAsState()
    val setStrData2 = viewModel.setStrData2.collectAsState()

    val sandData1 = viewModel.sandData1.collectAsState()
    val sandData2 = viewModel.sandData2.collectAsState()

    Log.d("LOG", "sandData1: ${sandData1.value.toString()}")
    Log.d("LOG", "sandData2: ${sandData2.value.toString()}")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("버튼",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
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
                        .weight(1f),
                    bluetoothViewModel = bluetoothViewModel,
                    labelStrList = setStrData1.value.toString(),
                    positionStrList = setStrData2.value.toString(),
                    sandDataStrList1 = sandData1.value.toString(),
                    sandDataStrList2 = sandData2.value.toString()
                )
                ButtonWidgetSettings(viewModel)
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
    sandDataStrList1: String? = "",
    sandDataStrList2: String? = ""
){
    sandDataStrList1?.let { Log.d("A", it) }

    val labelList = if (labelStrList != null) {
        labelStrList.split(",").toMutableList()
    }else{
        mutableListOf()
    }
    val positionList = if (positionStrList != null){
        positionStrList.split(",").toMutableList()
    }else{
        mutableListOf()
    }

    val sandDataList1 = if (sandDataStrList1 != null) {
        sandDataStrList1.split(",").toMutableList()
    }else{
        mutableListOf()
    }
    val sandDataList2 = if (sandDataStrList2 != null) {
        sandDataStrList2.split(",").toMutableList()
    }else{
        mutableListOf()
    }

    Surface(
        modifier = modifier
    ) {
        LazyColumn {
            items(labelList.size){ index ->
                ButtonCell(
                    bluetoothViewModel,
                    label = labelList[index],
                    position = positionList[index].toIntOrNull() ?: 0,
                    sandDataList1[index],
                    sandDataList2[index]
                )
            }
        }

    }
}

@Preview
@Composable
fun Test(){
    Surface {
        Column {
            Text(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                text = "asdasd"
            )
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = "asdasd"
            )
        }
    }
}

@Composable
fun ButtonCell(
    bluetoothViewModel: BluetoothControlViewModel = viewModel(),
    label: String = "",
    position: Int = 0,
    onPressed: String = "",
    onReleased: String = ""
) {
    var isPressed by remember { mutableStateOf(false) }

    var push by remember { mutableStateOf("") }
    var up by remember { mutableStateOf("") }

    push = onPressed
    up = onReleased

    Log.d("LOG", "label: $label")
    Log.d("LOG", "position: $position")
    Log.d("LOG", "onPressed: $onPressed")
    Log.d("LOG", "onReleased: $onReleased")

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
                                bluetoothViewModel.writeData(push)
                                tryAwaitRelease()
                                bluetoothViewModel.writeData(up)
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
    viewModel: WidgetViewModel = viewModel()
){
    val thisNumber = remember { mutableIntStateOf(0) }
    val buttonWidgetDataList = remember { mutableStateListOf<ButtonWidgetData>() }

    val labelStrData = remember { mutableStateListOf("") }
    val positionStrData = remember { mutableStateListOf("") }
    val pushData = remember { mutableStateListOf("") }
    val upData = remember { mutableStateListOf("") }

    viewModel.setStrData1(labelStrData.joinToString(",") )
    viewModel.setStrData2(positionStrData.joinToString(",") )
    viewModel.sandData1(pushData.joinToString(",") )
    viewModel.sandData2(upData.joinToString(",") )

    if(buttonWidgetDataList.size <= 0){
        buttonWidgetDataList.add(
            ButtonWidgetData("", "0", "", "")
        )
    }

    buttonWidgetDataList.forEachIndexed{ index, data ->
        labelStrData[index] = data.label
        positionStrData[index] = data.position
        pushData[index] = data.push
        upData[index] = data.up
    }
    Column {
        LazyRow {
            items(buttonWidgetDataList.size) { index ->
                TitleTag("${index + 1} 번"){
                    thisNumber.intValue = index
                }
            }
            item {
                TitleTag("+추가"){ buttonWidgetDataList.add(
                    ButtonWidgetData("", "0", "", "")
                )
                    labelStrData.add("")
                    positionStrData.add("")
                    pushData.add("")
                    upData.add("")
                    thisNumber.intValue = buttonWidgetDataList.size - 1
                    Log.d("num" ,"${buttonWidgetDataList.size}")
                }
            }
        }

        ButtonWidgetSettingsItem(
            buttonWidgetDataList[thisNumber.intValue].label,
            buttonWidgetDataList[thisNumber.intValue].position,
            buttonWidgetDataList[thisNumber.intValue].push,
            buttonWidgetDataList[thisNumber.intValue].up,
            labelField = {text -> buttonWidgetDataList[thisNumber.intValue].label = text},
            textPosition = {position -> buttonWidgetDataList[thisNumber.intValue].position = position},
            pushField = {text -> buttonWidgetDataList[thisNumber.intValue].push = text},
            upField = {text -> buttonWidgetDataList[thisNumber.intValue].up = text},
        )
    }
}

@Preview
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

@Preview
@Composable
fun ButtonWidgetSettingsItem(
    label: String = "",
    position: String = "0",
    push: String = "",
    up: String = "",
    labelField: (String) -> Unit = {},
    textPosition: (String) -> Unit = {},
    pushField: (String) -> Unit = {},
    upField: (String) -> Unit = {},

){
    Surface(
        modifier = Modifier
            .padding(3.dp),
        shape = RoundedCornerShape(5.dp),
        shadowElevation = 3.dp
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = label,
                onValueChange = {labelField(it)},
                label = { Text("라벨") }
            )
            Row {
                IconButton(
                    onClick = {},
                    enabled = position.toInt() == 0
                ){
                    Icon(
                        modifier = Modifier
                            .clickable { textPosition("0") },
                        painter = painterResource(id = R.drawable.text_left),
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = {},
                    enabled = position.toInt() == 1
                ){
                    Icon(
                        modifier = Modifier
                            .clickable { textPosition("1") },
                        painter = painterResource(id = R.drawable.text_center),
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = {},
                    enabled = position.toInt() == 2
                ){
                    Icon(
                        modifier = Modifier
                            .clickable { textPosition("2") },
                        painter = painterResource(id = R.drawable.text_right),
                        contentDescription = ""
                    )
                }
            }
            HorizontalDivider(thickness = 2.dp)
            Text(
                modifier = Modifier
                    .padding(3.dp),
                text = "데이터 전송"
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = push,
                onValueChange = { pushField(it) },
                label = { Text("push") },

            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = up,
                onValueChange = { upField(it) },
                label = { Text("up") }
            )

        }
    }
}


class ButtonWidgetData(
    label: String = "",
    position: String = "0",
    push: String = "",
    up: String = "",
){
    var label by mutableStateOf(label)
    var position by mutableStateOf(position)
    var push by mutableStateOf(push)
    var up by mutableStateOf(up)
}