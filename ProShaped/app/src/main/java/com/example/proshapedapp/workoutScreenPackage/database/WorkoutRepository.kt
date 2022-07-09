package com.example.proshapedapp.workoutScreenPackage.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDatabaseDao: WorkoutDatabaseDao) {
    val readAllData: LiveData<List<WorkoutItem>> = workoutDatabaseDao.getAll()

    suspend fun addWorkout(workoutItem: WorkoutItem){
        workoutDatabaseDao.insert(workoutItem)
    }

    suspend fun updateWorkout(workoutItem: WorkoutItem){
        workoutDatabaseDao.update(workoutItem)
    }

    suspend fun deleteWorkout(workoutItem: WorkoutItem){
        workoutDatabaseDao.delete(workoutItem)
    }
}