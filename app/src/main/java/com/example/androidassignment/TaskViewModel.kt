package com.example.androidassignment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    fun insert(context: Context, task_viewmode: Task) {
        TaskRepository.insert(context, task_viewmode)
    }

    fun delete(context: Context, task_viewmodel: Task) {
        TaskRepository.delete(context, task_viewmodel)
    }


    fun getALLData(context: Context): LiveData<List<Task>> {
        return TaskRepository.getAll(context)
    }
}



