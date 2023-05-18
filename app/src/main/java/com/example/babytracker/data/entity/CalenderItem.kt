package com.example.babytracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calender")
data class CalenderItem(@PrimaryKey(autoGenerate = true)
                        val itemId: Int,
                        val itemName:String,
                        val itemDate: String,
                        val itemTime:String,)
