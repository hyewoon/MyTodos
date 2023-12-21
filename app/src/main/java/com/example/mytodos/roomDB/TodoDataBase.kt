package com.example.mytodos.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    //싱글톤 패턴으로 이용하기 위해 추가한 부분
    //데이터베이스를 여기서 인스턴스화하여 사용한다.
    companion object {
        @Volatile
        private var INSTANCE: TodoDataBase? = null

        fun getInstance(context: Context): TodoDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDataBase::class.java,
                        "todo"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}