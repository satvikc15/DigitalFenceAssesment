package com.example.myshoppinglist

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun listDao(): ListDAO
}