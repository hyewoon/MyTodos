package com.example.mytodos.roomDB

import android.app.Application
import androidx.lifecycle.LiveData

class TodoRepository(application: Application) {

    var db : TodoDataBase = TodoDataBase.getInstance(application)!!

    val todoDao : TodoDao = db.todoDao()
    val todos : LiveData<List<Todo>> = db.todoDao().getAll()

    fun getAll() : LiveData<List<Todo>> {
        return  todos
    }

    fun deleteAll(){
        todoDao.deleteAll()
    }

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

   suspend fun update(todo: Todo){
     todoDao.update(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

}