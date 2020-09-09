package com.example.androidproj1.Models.UI

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Table")
data class Movie(
    val title: String,
    val popularity: Double,
    val imgPath: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)