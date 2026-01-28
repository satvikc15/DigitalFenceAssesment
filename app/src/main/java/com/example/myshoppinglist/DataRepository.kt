package com.example.myshoppinglist

import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

class DataRepository(private val listDAO: ListDAO){
    fun getAllList(): Flow<List<ListEntity>> = listDAO.getAllList()

    fun getAList(id:Long) : Flow<ListEntity> { return listDAO.getAList(id) }

    suspend fun add(listEntity: ListEntity) {listDAO.add(listEntity)}

    suspend fun update(listEntity: ListEntity) { listDAO.update(listEntity) }

    suspend fun delete(listEntity: ListEntity) { listDAO.delete(listEntity) }
}