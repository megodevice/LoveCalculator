package com.example.lovecalculator.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveAPI {
    @GET("getPercentage")
    fun getLove(

        @Query("fname") firstName: String,

        @Query("sname") secondName: String,

        @Header("X-RapidAPI-Key") key: String = "5db1575fb9msh6122d515149eaf0p1c6c14jsncf06093d8182",

        @Header("X-RapidAPI-Host") host: String = "love-calculator.p.rapidapi.com"

    ): Call<LoveModel>
}