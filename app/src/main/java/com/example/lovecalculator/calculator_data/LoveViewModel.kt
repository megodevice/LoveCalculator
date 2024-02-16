package com.example.lovecalculator.calculator_data

interface LoveViewModel {
    fun showResult(loveModel: LoveModel)
    fun showError(message: String)
}