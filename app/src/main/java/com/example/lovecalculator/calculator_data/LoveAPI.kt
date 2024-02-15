package com.example.lovecalculator.calculator_data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoveAPI {
    @GET("getPercentage")
    fun getLove(
        @Query("fname") fname: String,
        @Query("sname") sname: String
    ) : Call<LoveModel>
}