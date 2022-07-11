package com.example.proshapedapp.workoutScreenPackage.database

import androidx.lifecycle.LiveData

class WorkoutRepository(private val workoutDatabaseDao: WorkoutDatabaseDao) {
    val readAllData: LiveData<List<WorkoutItem>> = workoutDatabaseDao.readAllData()

    suspend fun addWorkout(workoutItem: WorkoutItem){
        workoutDatabaseDao.addWorkout(workoutItem)
    }

//    suspend fun updateWorkout(workoutItem: WorkoutItem){
//        workoutDatabaseDao.update(workoutItem)
//    }
//
//    suspend fun deleteWorkout(workoutItem: WorkoutItem){
//        workoutDatabaseDao.delete(workoutItem)
//    }
//
//    suspend fun getById(workoutItem: WorkoutItem, id: Int){
//        workoutDatabaseDao.getById(id)
//    }
//
//    suspend fun getByName(workoutItem: WorkoutItem, name: String){
//        workoutDatabaseDao.getByName(name)
//    }
}