package com.example.proshapedapp.workoutScreenPackage.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWorkout(workout: WorkoutItem)

    @Query("SELECT * FROM workout_list ORDER BY itemId ASC")
    fun readAllData(): LiveData<List<WorkoutItem>>
}