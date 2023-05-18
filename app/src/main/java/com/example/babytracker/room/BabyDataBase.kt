package com.example.babytracker.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.babytracker.data.entity.Baby

@Database(entities = [Baby::class], version =4)
abstract class BabyDataBase :RoomDatabase() {

    abstract fun babyDao() : BabyDAO

    companion object{
        @Volatile
        private var instance : BabyDataBase? =null

        fun getDatabase(contex : Context) : BabyDataBase{
            return instance ?: synchronized(this){
                val database = Room.databaseBuilder(
                    contex.applicationContext,
                    BabyDataBase::class.java,
                    "baby_database"
                ).fallbackToDestructiveMigration().build()

                instance = database
                database
            }
        }
    }

}