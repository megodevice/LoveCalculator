package com.example.lovecalculator.ui.calculator

import androidx.lifecycle.MutableLiveData
import com.example.lovecalculator.remote.LoveAPI
import com.example.lovecalculator.remote.LoveModel
import com.example.lovecalculator.room.LoveHistoryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CalculatorRepository @Inject constructor(
    private val loveAPI: LoveAPI,
    private val db: LoveHistoryDao,
) {
    fun getPercentage(
        firstName: String,
        secondName: String,
        result: MutableLiveData<LoveModel>,
        message: MutableLiveData<String>
    ) {
        if (firstName.isNotEmpty() && secondName.isNotEmpty())
            loveAPI.getLove(firstName, secondName).enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    response.body()?.let {loveResult ->
                        result.postValue(loveResult)
                        db.add(loveResult)
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    message.postValue(t.localizedMessage ?: "Error")
                }
            })
        else {
            message.postValue("Type a names")
        }
    }
}