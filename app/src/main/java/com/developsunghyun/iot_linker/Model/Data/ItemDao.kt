package com.developsunghyun.iot_linker.Model.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: WidgetData)

    @Update
    suspend fun update(item: WidgetData)

    @Delete
    suspend fun delete(item: WidgetData)

    @Query("SELECT * from widget WHERE id = :id")
    fun getItem(id: Int): Flow<WidgetData>

    @Query("SELECT * from widget ORDER BY id ASC")
    fun getAllItems(): Flow<List<WidgetData>>
}