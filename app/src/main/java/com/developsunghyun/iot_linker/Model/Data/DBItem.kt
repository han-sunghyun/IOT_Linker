package com.developsunghyun.iot_linker.Model.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class DBItemData(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var layoutName: String,
    var layoutType: String,
    var modul: String
)

@Entity(tableName = "widget")
data class WidgetData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var setIntData1: Int,
    var setIntData2: Int,
    var setIntData3: Int,
    var setIntData4: Int,
    var setStrData1: String,
    var setStrData2: String,
    var setStrData3: String,
    var setStrData4: String
)