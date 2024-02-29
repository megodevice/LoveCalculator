package com.example.lovecalculator.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.remote.LoveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val repository: CalculatorRepository) : ViewModel() {
    private val _result: MutableLiveData<LoveModel> = MutableLiveData()
    private val _message: MutableLiveData<String> = MutableLiveData()

    fun getPercentage(firstName: String, secondName: String) {
        repository.getPercentage(firstName, secondName, _result, _message)
    }

    val result: LiveData<LoveModel> = _result
    val message: LiveData<String> = _message

}