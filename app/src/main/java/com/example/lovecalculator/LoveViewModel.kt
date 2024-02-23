package com.example.lovecalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.remote.LoveModel
import com.example.lovecalculator.remote.LoveService

class LoveViewModel : ViewModel() {
    private val repository = Repository(LoveService().api)
    private val _result: MutableLiveData<LoveModel> = MutableLiveData()
    private val _message: MutableLiveData<String> = MutableLiveData()

    fun getPercentage(fname: String, sname: String) {
        repository.getPercentage(fname, sname, _result, _message)

    }

    val result: LiveData<LoveModel> = _result
    val message: LiveData<String> = _message


}