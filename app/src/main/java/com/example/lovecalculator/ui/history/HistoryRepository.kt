package com.example.lovecalculator.ui.history

import androidx.lifecycle.LiveData
import com.example.lovecalculator.remote.LoveModel
import com.example.lovecalculator.room.LoveHistoryDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(
    private val db: LoveHistoryDao,
) {
    fun getHistory(): LiveData<List<LoveModel>> = db.getAll()

    fun delete(loveModel: LoveModel) {
        db.delete(loveModel)
    }

    fun update(loveModel: LoveModel) {
        db.update(loveModel)
    }
}