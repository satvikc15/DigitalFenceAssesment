package com.example.myshoppinglist

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("shopping_items")
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,

    @ColumnInfo("Item_Name")
    val name:String="",

    @ColumnInfo("Quantity")
    val quant:Int=0,

    @ColumnInfo("Purchased")
    val purchased:Boolean=false,

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = 0L
)