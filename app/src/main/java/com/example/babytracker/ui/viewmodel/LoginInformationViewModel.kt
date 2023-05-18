package com.example.babytracker.ui.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.babytracker.data.entity.Baby
import com.example.babytracker.data.repo.Repository
import com.example.babytracker.room.BabyDataBase
import com.example.babytracker.room.CalenderItemDataBase
import kotlinx.coroutines.launch


class LoginInformationViewModel(application: Application): AndroidViewModel(application){
    private val repository : Repository

    init{
        val babyDao = BabyDataBase.getDatabase(application).babyDao()
        val calenderItemDAO = CalenderItemDataBase.getDatabase(application).calenderDao()
        repository = Repository(babyDao,calenderItemDAO)
    }
    fun insertBaby(babyDataClass: Baby) = viewModelScope.launch {
        repository.insert(babyDataClass)
    }

}