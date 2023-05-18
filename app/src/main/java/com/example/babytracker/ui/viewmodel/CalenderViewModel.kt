package com.example.babytracker.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.babytracker.data.entity.CalenderItem
import com.example.babytracker.data.repo.Repository
import com.example.babytracker.room.BabyDataBase
import com.example.babytracker.room.CalenderItemDataBase
import kotlinx.coroutines.launch

class CalenderViewModel(application: Application): AndroidViewModel(application) {

    private val repository : Repository

    init{
        val calenderItemDAO = CalenderItemDataBase.getDatabase(application).calenderDao()
        val babyDao = BabyDataBase.getDatabase(application).babyDao()
        repository = Repository(babyDao,calenderItemDAO)
    }

    fun insertCalender(calenderItem: CalenderItem) = viewModelScope.launch {
        repository.insertCalender(calenderItem)
    }
    suspend fun getData() = repository.getCalender()

}