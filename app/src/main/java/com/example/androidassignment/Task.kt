package com.example.androidassignment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    var description: String,
    var pirioty: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null


}