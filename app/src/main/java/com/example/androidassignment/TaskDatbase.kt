package com.example.androidassignment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the note class

@Database(entities = [Task::class], version = 6, exportSchema = false)

abstract class TaskDatbase : RoomDatabase() {


    abstract fun task_dao(): TaskDao


    companion object {
        @Volatile
        var instance: TaskDatbase? = null
        private const val DATABASE_NAME = "User"

        fun getInstance(context: Context): TaskDatbase? {
            if (instance == null) {
                synchronized(TaskDatbase::class.java)
                {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context, TaskDatbase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return instance
        }

    }


}