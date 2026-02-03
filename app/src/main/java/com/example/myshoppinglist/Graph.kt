package com.example.myshoppinglist

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: Database

    val DataRepository by lazy{
           DataRepository(listDAO = database.listDao())
    }
    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context,
            Database::class.java,
            "shopping_database.db"
        ).build()
    }
}