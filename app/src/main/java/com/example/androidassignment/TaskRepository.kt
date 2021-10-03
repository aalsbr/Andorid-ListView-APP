package com.example.androidassignment

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class TaskRepository {

    companion object {
        var task_database: TaskDatbase? = null

        private fun intialiseDB(context: Context): TaskDatbase? {
            return TaskDatbase.getInstance(context)!!
        }

        fun insert(context: Context, user: Task) {
            task_database = intialiseDB(context)

            CoroutineScope(IO).launch {
                task_database!!.task_dao().insert(user)
            }
        }

        fun delete(context: Context, user: Task) {
            task_database = intialiseDB(context)

            CoroutineScope(IO).launch {
                task_database!!.task_dao().delete(user)
            }
        }

        fun getAll(context: Context): LiveData<List<Task>> {
            task_database = intialiseDB(context)
            return task_database!!.task_dao().getAll()
        }
    }


}