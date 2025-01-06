package com.developsunghyun.iot_linker.View.Components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.developsunghyun.iot_linker.R

@Preview(heightDp = 400)
@Composable
fun TestView(){
    val label = remember { mutableStateListOf("", "", "") }
    val imageButton = remember { mutableStateListOf(false, false, false) }

    WidgetSettingsView(
        parametersTextList = listOf(label[0], label[1], label[2]),
        parametersHintList = listOf("라벨1", "라벨2", "라벨3"),
        parametersFieldList = listOf({text -> label[0] = text}, {text -> label[1] = text}, {text -> label[2] = text}),

        imageButtonIconList = listOf(R.drawable.text_left, R.drawable.text_center, R.drawable.text_right),
        imageButtonEnableList = listOf(imageButton[0], imageButton[1], imageButton[2]),
        imageButtonClickList = listOf({imageButton[0] = !imageButton[0]}, {imageButton[1] = !imageButton[1]}, {imageButton[2] = !imageButton[2]}),

        dataWriteTextList = listOf("write1", "write2"),
        dataWriteHintList = listOf("write1", "write2"),
        dataWriteFieldList = listOf({}, {}),
        dataWriteCheckList = listOf(false, true),
        dataWriteCheckBoxList = listOf({}, {}),
        guideWriteTextList = listOf("가이드"),

        dataReadTextList = listOf("read1", "read2"),
        dataReadHintList = listOf("read1", "read2"),
        dataReadFieldList = listOf({}, {}),
        guideReadTextList = listOf("가이드")
    )

}

@Composable
fun WidgetSettingsView(
    parametersTextList: List<String>? = null,
    parametersHintList: List<String>? = null,
    parametersFieldList: List<((String) -> Unit)>? = null,

    imageButtonIconList: List<Int>? = null,
    imageButtonEnableList: List<Boolean>? = null,
    imageButtonClickList: List<(() -> Unit)>? = null,

    dataWriteTextList: List<String>? = null,
    dataWriteHintList: List<String>? = null,
    dataWriteFieldList: List<((String) -> Unit)>? = null,
    dataWriteCheckList: List<Boolean>? = null,
    dataWriteCheckBoxList: List<(Boolean) -> Unit>? = null,
    guideWriteTextList: List<String>? = null,

    dataReadTextList: List<String>? = null,
    dataReadHintList: List<String>? = null,
    dataReadFieldList: List<((String) -> Unit)>? = null,
    guideReadTextList: List<String>? = null
){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        Surface(
            modifier = Modifier
        ) {
            var showDialog by remember { mutableStateOf(false) }
            var guideText by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {

                //==========

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp, end = 10.dp),
                        text = "위젯 파라미터"
                    )
                    HorizontalDivider(thickness = 2.dp)
                }

                //==========

                if(parametersFieldList != null && parametersTextList != null && parametersHintList != null){
                    if((parametersFieldList.size == parametersTextList.size) &&
                        (parametersFieldList.size == parametersHintList.size)){
                        Column {
                            for(index in parametersFieldList.indices){
                                OutlinedTextField(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    value = parametersTextList[index],
                                    onValueChange = { parametersFieldList[index](it) },
                                    label = { Text(text = parametersHintList[index]) }
                                )
                            }
                        }
                    }else{
                        Log.e("LOG.E", "parameters 인자중 List 개수가 다릅니다.")
                    }
                }else{
                    Log.e("LOG.E", "parameters 인자중 하나 이상이 null 입니다.")
                }

                //==========

                if(imageButtonIconList != null && imageButtonEnableList != null && imageButtonClickList != null){
                    if((imageButtonIconList.size == imageButtonClickList.size) &&
                        (imageButtonIconList.size == imageButtonEnableList.size)){
                        Row {
                            for(index in imageButtonIconList.indices){
                                IconButton(
                                    onClick = { imageButtonClickList[index].invoke() }
                                ){
                                    Icon(
                                        painter = painterResource(id = imageButtonIconList[index]),//R.drawable.text_left
                                        tint = if(imageButtonEnableList[index]) Color(color = 0xFF000000)
                                        else Color(color = 0x80000000),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }else{
                        Log.e("LOG.E", "imageButton 인자중 List 개수가 다릅니다.")
                    }
                }else{
                    Log.e("LOG.E", "imageButton 인자중 하나 이상이 null 입니다.")
                }

                //==========

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp, end = 10.dp),
                        text = "데이터 송신"
                    )
                    HorizontalDivider(thickness = 2.dp)
                }

                //==========

                if(dataWriteFieldList != null && dataWriteTextList != null &&
                    dataWriteHintList != null){

                    if((dataWriteTextList.size == dataWriteHintList.size) &&
                        (dataWriteTextList.size == dataWriteFieldList.size)){

                        Column {
                            for(index in dataWriteTextList.indices){
                                Column (
                                ) {
                                    if(dataWriteCheckList != null) {
                                        Checkbox(
                                            checked = dataWriteCheckList[index],
                                            onCheckedChange = { dataWriteCheckBoxList?.get(index)
                                                ?.let { it1 -> it1(it) } }
                                        )
                                    }
                                    Row(
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .weight(1f),
                                            value = dataWriteTextList[index],
                                            onValueChange = { dataWriteFieldList[index](it) },
                                            label = { Text(text = dataWriteHintList[index]) },
                                            enabled = if(dataWriteCheckList != null){
                                                dataWriteCheckList[index]
                                            }else{
                                                true
                                            }

                                        )
                                        if(guideWriteTextList != null){
                                            IconButton(
                                                modifier = Modifier,
                                                onClick = {
                                                    showDialog = true
                                                    guideText = guideWriteTextList.getOrElse(index){""}
                                                }
                                            ) {
                                                Icon(
                                                    modifier = Modifier
                                                        .size(20.dp)
                                                        .fillMaxHeight(),
                                                    painter = painterResource(id = R.drawable.question),
                                                    contentDescription = "")

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        Log.e("LOG.E", "parameters 인자중 List 개수가 다릅니다.")
                    }
                }else{
                    Log.e("LOG.E", "parameters 인자중 하나 이상이 null 입니다.")
                }

                //==========

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp, end = 10.dp),
                        text = "데이터 수신"
                    )
                    HorizontalDivider(thickness = 2.dp)
                }

                //==========

                if(dataReadFieldList != null && dataReadTextList != null &&
                    dataReadHintList != null && guideReadTextList != null){

                    if((dataReadTextList.size == dataReadHintList.size) &&
                        (dataReadTextList.size == dataReadFieldList.size)){

                        Column {
                            for(index in dataReadTextList.indices){
                                Row(
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    OutlinedTextField(
                                        modifier = Modifier
                                            .weight(1f),
                                        value = dataReadTextList[index],
                                        onValueChange = { dataReadFieldList[index](it) },
                                        label = { Text(text = dataReadHintList[index]) },
                                    )
                                    if(guideWriteTextList != null){
                                        IconButton(
                                            modifier = Modifier,
                                            onClick = {
                                                showDialog = true
                                                guideText = guideReadTextList.getOrElse(index){""}
                                            }
                                        ) {
                                            Icon(
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .fillMaxHeight(),
                                                painter = painterResource(id = R.drawable.question),
                                                contentDescription = "")

                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        Log.e("LOG.E", "parameters 인자중 List 개수가 다릅니다.")
                    }
                }else{
                    Log.e("LOG.E", "parameters 인자중 하나 이상이 null 입니다.")
                }

                //==========
            }
            if(showDialog) {
                DiaLogView(text = guideText){ test -> showDialog = test }
            }
        }
    }

}

@Composable
fun DiaLogView(
    text: String,
    showDialog: (Boolean) -> Unit,
){

    Dialog(onDismissRequest = { showDialog(false) }) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = text)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { showDialog(false) }) {
                    Text("닫기")
                }
            }
        }
    }
}