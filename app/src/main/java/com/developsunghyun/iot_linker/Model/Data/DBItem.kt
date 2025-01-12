package com.developsunghyun.iot_linker.Model.Data

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class DBItemData(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var layoutName: String = "",
    var layoutType: String = "",
    var moduleStringList: String = ""
)

@Entity(tableName = "widget")
data class WidgetData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var widgetType: String = "",
    var name: String = "",
    var image: String = "",

    var setStrData1: String = "",
    var setStrData2: String = "",
    var setStrData3: String = "",
    var setStrData4: String = "",

    var writeData1Enable: String = "",
    var writeData1: String = "",
    var writeData2Enable: String = "",
    var writeData2: String = "",
    var writeData3Enable: String = "",
    var writeData3: String = "",
    var writeData4Enable: String = "",
    var writeData4: String = "",

    var readData1: String = "",
    var readData2: String = "",
    var readData3: String = "",
    var readData4: String = "",
)