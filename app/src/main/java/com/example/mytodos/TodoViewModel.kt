package com.example.mytodos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mytodos.roomDB.Todo
import com.example.mytodos.roomDB.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    val repository  = TodoRepository(application)
   // var todos : LiveData<List<Todo>> = repository.getAll()



    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodoViewModel(application) as T
        }

    }
    fun getAll() : LiveData<List<Todo>>{
        return repository.getAll()
    }
    fun deleteAll(){
        repository.deleteAll()
    }

    fun insert(todo: Todo){
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun update(todo: Todo) {
        viewModelScope.launch {
            repository.update(todo)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }

}