package com.example.proshapedapp.workoutScreenPackage.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDatabaseDao {
    @Query("SELECT * from workout_list")
    fun getAll(): LiveData<List<WorkoutItem>>

    @Query("SELECT * from workout_list where itemId = :id")
    fun getById(id: Int): WorkoutItem

    @Query("SELECT * from workout_list where item_name = :name")
    fun getByName(name: String): WorkoutItem
    //use this to separate by exercise

    @Insert
    suspend fun insert(item: WorkoutItem)

    @Update
    suspend fun update(item: WorkoutItem)

    @Delete
    suspend fun delete(item: WorkoutItem)
}