package com.example.proshapedapp.workoutScreenPackage.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutViewModel(application: Application): AndroidViewModel(application){
    /*val readAllData: LiveData<List<WorkoutItem>>*/
    private val repository: WorkoutRepository

    init {
        val workoutDao = WorkoutDatabase.getDatabase(application).workoutDao()
        repository = WorkoutRepository(workoutDao)
        /*readAllData = repository.readAllData*/
    }

    fun addWorkout(workoutItem: WorkoutItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWorkout(workoutItem)
        }
    }

//    fun updateWorkout(workoutItem: WorkoutItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateWorkout(workoutItem = workoutItem)
//        }
//    }
//
//    fun deleteWorkout(workoutItem: WorkoutItem) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteWorkout(workoutItem = workoutItem)
//        }
//    }

    //implement something for getByName
}