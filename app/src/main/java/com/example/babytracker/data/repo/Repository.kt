package com.example.babytracker.data.repo

import com.example.babytracker.data.entity.Baby
import com.example.babytracker.data.entity.CalenderItem
import com.example.babytracker.room.BabyDAO
import com.example.babytracker.room.CalenderItemDAO

class Repository(private val babyDao: BabyDAO,private val calenderitemDao:CalenderItemDAO) {

    suspend fun getCalender() = calenderitemDao.getCalender()

    suspend fun getBaby() = babyDao.getBaby()

    suspend fun insert(babyDataClass: Baby){
        babyDao.insertBaby(babyDataClass)
    }
    suspend fun insertCalender(calenderItem: CalenderItem){
        calenderitemDao.insertCalender(calenderItem)
    }

}