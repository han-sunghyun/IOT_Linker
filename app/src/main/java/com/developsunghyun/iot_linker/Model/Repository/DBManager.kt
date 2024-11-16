package com.developsunghyun.iot_linker.Model.Repository

import android.content.Context
import androidx.lifecycle.ViewModel
import com.developsunghyun.iot_linker.Model.Data.DBItemData

class DBManager(context: Context) : ViewModel() {
    val dbContext = context
    val dao = DataBase.getDatabase(dbContext).dbItemDao()

    suspend fun testInsert(){
        dao.insert(DBItemData(layoutName = "name", layoutType = "type", modul = "module"))
    }

}