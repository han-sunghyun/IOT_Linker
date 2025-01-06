package com.developsunghyun.iot_linker.View.Widget

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developsunghyun.iot_linker.R
import com.developsunghyun.iot_linker.View.Components.WidgetSettingsView
import com.developsunghyun.iot_linker.ViewModel.BluetoothControlViewModel
import com.developsunghyun.iot_linker.ViewModel.WidgetViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SwitchWidgetScreen(
    bluetoothViewModel: BluetoothControlViewModel
){
    val viewModel: WidgetViewModel = viewModel()

    val setStrData1 = viewModel.setStrData1.collectAsState()

    val writeData1 = viewModel.sandData1.collectAsState()
    val writeData2 = viewModel.sandData2.collectAsState()


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
                SwitchWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    bluetoothViewModel = bluetoothViewModel,
                    labelStrList = setStrData1.value.toString(),

                    writeData1List = writeData1.value.toString(),
                    writeData2List = writeData2.value.toString(),

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
    writeData1List: String? = "false,true,false,true",
    writeData2List: String? = "false,true,false,true"
){

    val labelList = labelStrList?.split(",")?.toMutableList() ?: mutableListOf()
    val switchOnWriteList = writeData1List?.split(",")?.toMutableList() ?: mutableListOf()
    val switchOffWriteList = writeData2List?.split(",")?.toMutableList() ?: mutableListOf()

    Surface(
        modifier = modifier
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState),
        ) {
            labelList.forEachIndexed { index, s ->
                SwitchCell(
                    bluetoothViewModel,
                    label = labelList[index],
                    on = switchOnWriteList[index].toString(),
                    off = switchOffWriteList[index].toString()
                )
            }
        }

    }
}

@Composable
fun SwitchCell(
    bluetoothViewModel: BluetoothControlViewModel = viewModel(),
    label: String,
    on: String,
    off: String,
){
    var switchState by remember { mutableStateOf(true) }


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
                    if(switchState) bluetoothViewModel.writeData(on)
                    else bluetoothViewModel.writeData(off)
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

    val switchOnData = remember { mutableStateListOf("") }
    val switchOffData = remember { mutableStateListOf("") }

    viewModel.setStrData1(labelStrData.joinToString(",") )

    viewModel.sandData1(switchOnData.joinToString(",") )
    viewModel.sandData2(switchOffData.joinToString(",") )


    if(switchWidgetDataList.size <= 0){
        switchWidgetDataList.add(
            SwitchWidgetData("", "", "")
        )
    }

    switchWidgetDataList.forEachIndexed{ index, data ->
        labelStrData[index] = data.label
        switchOnData[index] = data.switchOn
        switchOffData[index] = data.switchOff

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
                    switchOnData.add("")
                    switchOffData.add("")
                    thisNumber.intValue = switchWidgetDataList.size - 1
                }
            }
        }

        WidgetSettingsView(
            parametersTextList = listOf(switchWidgetDataList[thisNumber.intValue].label),
            parametersHintList = listOf("라벨"),
            parametersFieldList = listOf(){text -> switchWidgetDataList[thisNumber.intValue].label = text},

            dataWriteTextList = listOf(
                switchWidgetDataList[thisNumber.intValue].switchOn,
                switchWidgetDataList[thisNumber.intValue].switchOff),
            dataWriteHintList = listOf("write1", "write2"),
            dataWriteFieldList = listOf(
                {text -> switchWidgetDataList[thisNumber.intValue].switchOn = text},
                {text -> switchWidgetDataList[thisNumber.intValue].switchOff = text}
            ),

            guideWriteTextList = listOf("가이드", "1"),
        )

    }
}


class SwitchWidgetData(
    label: String = "",

    switchOn: String = "",
    switchOff: String = "",
){
    var label by mutableStateOf(label)
    var switchOn by mutableStateOf(switchOn)
    var switchOff by mutableStateOf(switchOff)

}