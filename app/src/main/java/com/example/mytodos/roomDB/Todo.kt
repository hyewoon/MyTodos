package com.example.mytodos.roomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val todoList : String
) {

}
