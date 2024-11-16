package com.developsunghyun.iot_linker.Model.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.developsunghyun.iot_linker.Model.Data.DBItemData
import com.developsunghyun.iot_linker.Model.Data.ItemDao
import com.developsunghyun.iot_linker.Model.Data.WidgetData

@Database(entities = [DBItemData::class, WidgetData::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun dbItemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DataBase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}