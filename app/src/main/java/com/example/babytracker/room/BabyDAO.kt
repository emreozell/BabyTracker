package com.example.babytracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.babytracker.data.entity.Baby


@Dao
interface BabyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBaby(babyDataClass: Baby)

    @Query("SELECT * FROM baby")
    suspend fun getBaby(): List<Baby>

}