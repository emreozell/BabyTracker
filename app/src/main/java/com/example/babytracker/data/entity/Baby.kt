package com.example.babytracker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "baby")
data class Baby(
    @PrimaryKey(autoGenerate = false)
    val babyid: Int=0,
    val babyFullName:String,
    val birthDate: String,
    val timeofBirth:String,
    val dueDate:String,
    val babyGender:String,
    val image:ByteArray,
){
    
}