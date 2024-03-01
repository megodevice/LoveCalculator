package com.example.lovecalculator.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.remote.LoveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val historyRepository: HistoryRepository) :
    ViewModel() {

    val history: LiveData<List<LoveModel>> = historyRepository.getHistory()

    fun deleteItem(loveModel: LoveModel) {
        historyRepository.delete(loveModel)
    }

    fun updateItem(loveModel: LoveModel) {
        historyRepository.update(loveModel)
    }
}