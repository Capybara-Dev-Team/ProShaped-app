package com.example.proshapedapp.workoutScreenPackage.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers

class WorkoutRepository(private val workoutDatabaseDao: WorkoutDatabaseDao) {
    val selectAll = workoutDatabaseDao.readAllData()

    suspend fun insertWorkout(workoutItem: WorkoutItem){
        Dispatchers.IO.apply {
            workoutDatabaseDao.addWorkout(workoutItem)
        }
    }

    suspend fun deleteWorkout(workoutItem: WorkoutItem){
        Dispatchers.IO.apply {
            workoutDatabaseDao.delete(workoutItem.itemId)
        }
    }

    suspend fun updateWorkout(isComplete: Boolean, id: Long){
        Dispatchers.IO.apply {
            workoutDatabaseDao.updateWorkout(isComplete, id)
        }
    }
}