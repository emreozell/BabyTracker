package com.example.babytracker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.babytracker.data.repo.Repository
import com.example.babytracker.room.BabyDataBase
import com.example.babytracker.room.CalenderItemDAO
import com.example.babytracker.room.CalenderItemDataBase

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repository : Repository

    init{
        val calenderItemDAO = CalenderItemDataBase.getDatabase(application).calenderDao()
        val babyDao = BabyDataBase.getDatabase(application).babyDao()
        repository = Repository(babyDao,calenderItemDAO)
    }

    suspend fun getData() = repository.getBaby()


}