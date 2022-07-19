package com.example.proshapedapp.workoutScreenPackage.database

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDatabaseDao {

    @Query("SELECT * FROM workout_list")
    fun readAllData(): Flow<List<WorkoutItem>>

    @Query("SELECT * FROM workout_list WHERE item_name = :item_name")
    suspend fun readByName(item_name: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorkout(workout: WorkoutItem)

    @Query("DELETE FROM workout_list WHERE itemId = :itemId")
    suspend fun delete(itemId: Long)

    @Query("UPDATE workout_list SET is_completed = :is_completed WHERE itemId = :itemId")
    suspend fun updateWorkout(is_completed: Boolean, itemId: Long)
}