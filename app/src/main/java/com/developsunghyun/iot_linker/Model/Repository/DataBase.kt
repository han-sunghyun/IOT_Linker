package com.developsunghyun.iot_linker.Model.Repository

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.developsunghyun.iot_linker.Model.Data.DBItemData
import com.developsunghyun.iot_linker.Model.Data.ItemDao
import com.developsunghyun.iot_linker.Model.Data.WidgetData
import java.util.concurrent.Executors

@Database(entities = [DBItemData::class, WidgetData::class], version = 3, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun dbItemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DataBase::class.java, "item_database")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            db.execSQL(
                                "INSERT INTO items (id, layoutName, layoutType, moduleStringList)" +
                                        " VALUES (1, 'test', 'test', 'test')")
                            db.execSQL(
                                "INSERT INTO items (id, layoutName, layoutType, moduleStringList)" +
                                        " VALUES (2, 'test2', 'test2', 'test2')")

                        }
                    }).build()
                    .also { Instance = it }
            }
        }
    }

}