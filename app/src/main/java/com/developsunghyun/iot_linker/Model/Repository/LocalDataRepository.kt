package com.developsunghyun.iot_linker.Model.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.developsunghyun.iot_linker.Model.Data.DBItemData
import com.developsunghyun.iot_linker.Model.Data.ItemDao

class LocalDataRepository(context: Context){
    val dbContext = context
    val dao = DataBase.getDatabase(dbContext).dbItemDao()

    suspend fun testInsert(){
//        dao.insert(DBItemData(layoutName = "name", layoutType = "type", moduleStringList = "module"))
    }
    fun getData(): LiveData<DBItemData> {
        return dao.getItem(0).asLiveData()
    }

}