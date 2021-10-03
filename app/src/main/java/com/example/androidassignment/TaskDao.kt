package com.example.androidassignment

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)


    @Delete
    fun delete(task: Task)


    @Query("SELECT * FROM Task ORDER BY pirioty DESC")
    fun getAll(): LiveData<List<Task>>


}