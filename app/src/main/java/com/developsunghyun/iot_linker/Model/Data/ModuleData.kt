package com.developsunghyun.iot_linker.Model.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class ModuleData(
    widgetType: String = "",

    setStrData1: String = "",
    setStrData2: String = "",
    setStrData3: String = "",
    setStrData4: String = "",

    writeData1Enable: String = "",
    writeData1: String = "",
    writeData2Enable: String = "",
    writeData2: String = "",
    writeData3Enable: String = "",
    writeData3: String = "",
    writeData4Enable: String = "",
    writeData4: String = "",

    readData1: String = "",
    readData2: String = "",
    readData3: String = "",
    readData4: String = ""
) {
    var widgetType by mutableStateOf(widgetType)

    var setStrData1 by mutableStateOf(setStrData1)
    var setStrData2 by mutableStateOf(setStrData2)
    var setStrData3 by mutableStateOf(setStrData3)
    var setStrData4 by mutableStateOf(setStrData4)

    var writeData1Enable by mutableStateOf(writeData1Enable)
    var writeData1 by mutableStateOf(writeData1)
    var writeData2Enable by mutableStateOf(writeData2Enable)
    var writeData2 by mutableStateOf(writeData2)
    var writeData3Enable by mutableStateOf(writeData3Enable)
    var writeData3 by mutableStateOf(writeData3)
    var writeData4Enable by mutableStateOf(writeData4Enable)
    var writeData4 by mutableStateOf(writeData4)

    var readData1 by mutableStateOf(readData1)
    var readData2 by mutableStateOf(readData2)
    var readData3 by mutableStateOf(readData3)
    var readData4 by mutableStateOf(readData4)
}