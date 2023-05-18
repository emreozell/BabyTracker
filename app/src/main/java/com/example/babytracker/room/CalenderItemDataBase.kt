package com.example.babytracker.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.babytracker.data.entity.CalenderItem

@Database(entities = [CalenderItem::class], version =4)
abstract class CalenderItemDataBase :RoomDatabase() {
    abstract fun calenderDao() : CalenderItemDAO

    companion object{
        @Volatile
        private var instance : CalenderItemDataBase? =null

        fun getDatabase(contex : Context) : CalenderItemDataBase{
            return instance ?: synchronized(this){
                val database = Room.databaseBuilder(
                    contex.applicationContext,
                    CalenderItemDataBase::class.java,
                    "calender_database"
                ).fallbackToDestructiveMigration().build()

                instance = database
                database
            }
        }
    }
}