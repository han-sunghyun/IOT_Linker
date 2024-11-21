package com.developsunghyun.iot_linker.Model.Repository

import android.content.Context
import com.developsunghyun.iot_linker.Model.Data.DBItemData

class LocalDataRepository(context: Context){
    val dbContext = context
    val dao = DataBase.getDatabase(dbContext).dbItemDao()

    suspend fun testInsert(){
        dao.insert(DBItemData(layoutName = "name", layoutType = "type", moduleStringList = "module"))
    }

}