package com.example.proshapedapp.workoutScreenPackage.database

import android.content.Context

object Graph {
    lateinit var database: WorkoutDatabase
        private set
    val workoutRepo by lazy {
        WorkoutRepository(database.workoutDao())
    }

    fun provide(context: Context){
        database = WorkoutDatabase.getDatabase(context)
    }
}