package com.developsunghyun.iot_linker.Model.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.developsunghyun.iot_linker.Model.Data.WidgetData

class LocalDataRepository(context: Context){
    private val dbContext = context
    private val dao = DataBase.getDatabase(dbContext).dbItemDao()

    suspend fun widgetDataInsert(
        widgetType: String = "",
        name: String = "",
        image: String = "",

        setStrData1: String = "",
        setStrData2: String = "",
        setStrData3: String = "",
        setStrData4: String = "",

        writeData1Enable: String = "true",
        writeData1: String = "",
        writeData2Enable: String = "true",
        writeData2: String = "",
        writeData3Enable: String = "true",
        writeData3: String = "",
        writeData4Enable: String = "true",
        writeData4: String = "",

        readData1: String = "",
        readData2: String = "",
        readData3: String = "",
        readData4: String = "",
    ){
        dao.insert(
            WidgetData(
                widgetType = widgetType,
                name = name,
                image = image,
                setStrData1 = setStrData1,
                setStrData2 = setStrData2,
                setStrData3 = setStrData3,
                setStrData4 = setStrData4,
                writeData1Enable = writeData1Enable,
                writeData1 = writeData1,
                writeData2Enable = writeData2Enable,
                writeData2 = writeData2,
                writeData3Enable = writeData3Enable,
                writeData3 = writeData3,
                writeData4Enable = writeData4Enable,
                writeData4 = writeData4,

                readData1 = readData1,
                readData2 = readData2,
                readData3 = readData3,
                readData4 = readData4,
            )
        )
    }
    fun getListData(): LiveData<List<WidgetData>> {
        return dao.getAllItems().asLiveData()
    }

    fun getData(id: Int): LiveData<WidgetData>{
        return dao.getItem(id).asLiveData()
    }

}