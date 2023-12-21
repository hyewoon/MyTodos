package com.example.mytodos.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    //어노테이션을 통해 room라이블러기가 TodoData를 insert/update/delete 하는 모든 기능을 정의

    @Insert
    suspend fun insert(todo : Todo)
    @Update
    suspend fun update(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)

    @Query( "SELECT * FROM Todo" )
    fun getAll() : LiveData<List<Todo>>
    @Query("DELETE FROM todo")
    fun deleteAll()

}