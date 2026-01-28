package com.example.myshoppinglist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDAO {
    @Query("Select * from `shopping_items`")
    fun getAllList(): Flow<List<ListEntity>>

    @Query("Select * from `shopping_items` where id=:id")
    fun getAList(id:Long):Flow<ListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(item: ListEntity)

    @Update()
    suspend fun update(item: ListEntity)

    @Delete()
    suspend fun delete(item: ListEntity)


}