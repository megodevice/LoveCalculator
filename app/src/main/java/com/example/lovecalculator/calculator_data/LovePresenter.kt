package com.example.lovecalculator.calculator_data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LovePresenter(
    private val loveAPI: LoveAPI,
    private val loveViewModel: LoveViewModel
) {
    fun getPercentage(fname: String, sname: String) {
        if (fname.isNotEmpty() && sname.isNotEmpty())
            loveAPI.getLove(fname, sname).enqueue(object : Callback<LoveModel> {
                override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                    response.body()?.let { result ->
                        loveViewModel.showResult(result)
                    }
                }

                override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                    loveViewModel.showError(t.localizedMessage ?: "Error")
                }
            })
        else
            loveViewModel.showError("Type a names")
    }
}