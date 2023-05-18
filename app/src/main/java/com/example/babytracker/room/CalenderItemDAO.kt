package com.example.babytracker.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.babytracker.data.entity.CalenderItem

@Dao
interface CalenderItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalender(calenderItem: CalenderItem)

    @Query("SELECT * FROM calender")
    suspend fun getCalender(): List<CalenderItem>
}