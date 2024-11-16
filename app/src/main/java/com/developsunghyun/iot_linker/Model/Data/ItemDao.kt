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
    suspend fun insert(item: DBItemData)

    @Update
    suspend fun update(item: DBItemData)

    @Delete
    suspend fun delete(item: DBItemData)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<DBItemData>

    @Query("SELECT * from items ORDER BY id ASC")
    fun getAllItems(): Flow<List<DBItemData>>
}