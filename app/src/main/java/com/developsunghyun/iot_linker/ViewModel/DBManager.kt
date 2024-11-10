package com.developsunghyun.iot_linker.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.developsunghyun.iot_linker.Model.Repository.DataBase

class DBManager(context: Context) : ViewModel() {
    val dbContext = context
    val dao = DataBase.getDatabase(dbContext).dbItemDao()

}