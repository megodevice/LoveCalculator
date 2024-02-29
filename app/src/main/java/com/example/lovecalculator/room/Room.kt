package com.example.lovecalculator.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.example.lovecalculator.remote.LoveModel

@Dao
interface LoveHistoryDao {
    @Query("SELECT * FROM history ORDER by firstName")
    fun getAll(): LiveData<List<LoveModel>>

    @Update
    fun update(love: LoveModel)

    @Insert
    fun add(love: LoveModel)

    @Delete
    fun delete(love: LoveModel)
}

@Database(entities = [LoveModel::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): LoveHistoryDao
}