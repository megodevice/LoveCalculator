package com.example.lovecalculator

import androidx.lifecycle.MutableLiveData
import com.example.lovecalculator.remote.LoveAPI
import com.example.lovecalculator.remote.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    private val loveAPI: LoveAPI,
) {
    fun getPercentage(
        fname: String,
        sname: String,
        result: MutableLiveData<LoveModel>,
        message: MutableLiveData<String>
    ) {
        if (fname.isNotEmpty() && sname.isNotEmpty())
            loveAPI.getLove(fname, sname).enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    response.body()?.let {
                        result.value = it
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    message.value = t.localizedMessage ?: "Error"
                }
            })
        else {
            message.value = "Type a names"
        }

    }
}